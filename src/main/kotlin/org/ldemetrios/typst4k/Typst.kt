package org.ldemetrios.typst4k

import kotlinx.serialization.json.Json
import org.ldemetrios.typst4k.orm.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.exec
import java.io.InputStream
import java.nio.file.Path

val FROM_STDIN: Path? = null

@PublishedApi
internal data class Command(val entries: MutableList<String>) {
    constructor(vararg args: String) : this(args.toMutableList())

    fun optionalKey(key: String, include: Boolean): Command {
        if (include) {
            entries.add(key)
        }
        return this
    }

    fun optionalEntry(key: String, value: Any?): Command {
        if (value != null) {
            entries.add(key)
            entries.add(value.toString())
        }
        return this
    }

    fun optionalArg(value: Any?): Command {
        if (value != null) {
            entries.add(value.toString())
        }
        return this
    }

    fun multiple(key: String, args: List<Any?>): Command {
        for (arg in args) {
            entries.add(key)
            entries.add(arg.toString())
        }
        return this
    }
}

class TypstException(exitCode: Int, message: String) :
    Exception("Typst compiler finished with exit code $exitCode: \n$message")

sealed interface TypstCompilerResult<out T> {
    fun <R> map(func: (T) -> R): TypstCompilerResult<R>
    fun orElse(value: @UnsafeVariance T): T
    fun orNull(): T? = (this as TypstCompilerResult<T?>).orElse(null)
    fun orElseThrow() = orElseThrow(::TypstException)
    fun orElseThrow(exceptionSupplier: (Int, String) -> Throwable): T

    data class Success<T>(val value: T) : TypstCompilerResult<T> {
        override fun <R> map(func: (T) -> R): TypstCompilerResult<R> = Success(func(value))
        override fun orElse(value: T): T = this.value
        override fun orElseThrow(exceptionSupplier: (Int, String) -> Throwable) = value
    }

    data class Failure(val exitCode: Int, val message: String) : TypstCompilerResult<Nothing> {
        override fun <R> map(func: (Nothing) -> R): TypstCompilerResult<R> = this
        override fun orElse(value: Nothing): Nothing = value
        override fun orElseThrow(exceptionSupplier: (Int, String) -> Throwable) =
            throw exceptionSupplier(exitCode, message)
    }
}

data class Typst(val executable: String = "typst") {
    @PublishedApi
    internal fun execute(arguments: Command, input: String? = null): TypstCompilerResult<String> {
        val (out, err, exit) = exec(listOf(executable) + arguments.entries, input)
        return if (exit == 0) {
            TypstCompilerResult.Success(out.joinToString(System.lineSeparator()))
        } else {
            TypstCompilerResult.Failure(exit, err.joinToString(System.lineSeparator()))
        }
    }

    @PublishedApi
    internal fun execute(vararg arguments: String): TypstCompilerResult<String> =
        execute(Command(arguments.toMutableList()))

    @PublishedApi
    internal val json = Json {
        ignoreUnknownKeys = true
        classDiscriminator = "func"
    }

    fun help() = execute("help")

    fun update(version: String? = null, forceDowngrade: Boolean = false) =
        execute(
            Command("update")
                .optionalKey("--force", forceDowngrade)
                .optionalArg(version)
        )

    val update = object : Update {
        override fun revert() = execute("update", "--revert")

        override fun help() = execute("update", "--help")
    }

    enum class DiagnosticFormat {
        HUMAN, SHORT;

        override fun toString(): String = this.name.lowercase()
    }

    object OutputFormat {
        // Unlike DiagnosticFormat, these are subject to change
        const val PDF = "pdf"
        const val PNG = "png"
        const val SVG = "svg"
    }

    fun compile(
        input: String,
        output: String? = null,
        root: String? = null,
        inputs: Map<String, String> = mapOf(),
        fontPath: String? = null,
        diagnosticFormat: DiagnosticFormat = DiagnosticFormat.HUMAN,
        format: String? = null,
        open: Boolean = false,
        ppi: Int? = null, /*= 144*/
        timings: String? = null,
    ) = execute(
        Command("compile")
            .optionalEntry("--root", root)
            .multiple("--input", inputs.map { it.key + "=" + it.value })
            .optionalEntry("--font-path", fontPath)
            .optionalEntry("--diagnostic-format", diagnosticFormat)
            .optionalEntry("--format", format)
            .optionalEntry("--open", open)
            .optionalEntry("--ppi", ppi)
            .optionalEntry("--timings", timings)
            .optionalArg(input.toString())
            .optionalArg(output)
    )

    fun compile(
        input: InputStream,
        output: String,
        root: String? = null,
        inputs: Map<String, String> = mapOf(),
        fontPath: String? = null,
        diagnosticFormat: DiagnosticFormat = DiagnosticFormat.HUMAN,
        format: String? = null,
        open: Boolean = false,
        ppi: Int? = null, /*= 144*/
        timings: String? = null,
    ) = execute(
        Command("compile")
            .optionalEntry("--root", root)
            .multiple("--input", inputs.map { it.key + "=" + it.value })
            .optionalEntry("--font-path", fontPath)
            .optionalEntry("--diagnostic-format", diagnosticFormat)
            .optionalEntry("--format", format)
            .optionalEntry("--open", open)
            .optionalEntry("--ppi", ppi)
            .optionalEntry("--timings", timings)
            .optionalArg("-")
            .optionalArg(output),
        input.readBytes().toString(Charsets.UTF_8)
    )

    val compile = object : Helping {
        override fun help() = execute("compile", "--help")
    }

    val query = object : Helping {
        override fun help() = execute("query", "--help")
    }

    @PublishedApi
    internal inline fun <reified T : TValue> queryRaw(
        input: String,
        selectorString: String,
        root: String? = null,
        inputs: Map<String, String>,
        fontPath: String? = null,
        diagnosticFormat: DiagnosticFormat = DiagnosticFormat.HUMAN
    ): TypstCompilerResult<TArray<T>> = execute(
        Command("query")
            .optionalEntry("--root", root)
            .multiple("--input", inputs.map { it.key + "=" + it.value })
            .optionalEntry("--font-path", fontPath)
            .optionalEntry("--diagnostic-format", diagnosticFormat)
            .optionalArg(input)
            .optionalArg(selectorString)
    ).map { deserializeTypstValue<TArray<T>>(it) }

    inline fun <reified T : TValue> query(
        input: String,
        selector: TSelector,
        root: String? = null,
        inputs: Map<String, String> = mapOf(),
        fontPath: String? = null,
        diagnosticFormat: DiagnosticFormat = DiagnosticFormat.HUMAN
    ) = queryRaw<T>(input, selector.toString(), root, inputs, fontPath, diagnosticFormat)

    inline fun <reified T : TValue> query(
        input: String,
        label: String,
        root: String? = null,
        inputs: Map<String, String> = mapOf(),
        fontPath: String? = null,
        diagnosticFormat: DiagnosticFormat = DiagnosticFormat.HUMAN
    ) = queryRaw<T>(input, "<$label>", root, inputs, fontPath, diagnosticFormat)

    @PublishedApi
    internal inline fun <reified T : TValue> queryRaw(
        input: InputStream,
        selectorString: String,
        root: String? = null,
        inputs: Map<String, String>,
        fontPath: String? = null,
        diagnosticFormat: DiagnosticFormat = DiagnosticFormat.HUMAN
    ): TypstCompilerResult<TArray<T>> = execute(
        Command("query")
            .optionalEntry("--root", root)
            .multiple("--input", inputs.map { it.key + "=" + it.value })
            .optionalEntry("--font-path", fontPath)
            .optionalEntry("--diagnostic-format", diagnosticFormat)
            .optionalArg("-")
            .optionalArg(selectorString),
        input.readBytes().toString(Charsets.UTF_8)
    ).map { deserializeTypstValue<TArray<T>>(it) }

    inline fun <reified T : TValue> query(
        input: InputStream,
        selector: TSelector,
        root: String? = null,
        inputs: Map<String, String> = mapOf(),
        fontPath: String? = null,
        diagnosticFormat: DiagnosticFormat = DiagnosticFormat.HUMAN
    ) = queryRaw<T>(input, selector.toString(), root, inputs, fontPath, diagnosticFormat)

    inline fun <reified T : TValue> query(
        input: InputStream,
        label: String,
        root: String? = null,
        inputs: Map<String, String> = mapOf(),
        fontPath: String? = null,
        diagnosticFormat: DiagnosticFormat = DiagnosticFormat.HUMAN
    ) = queryRaw<T>(input, "<$label>", root, inputs, fontPath, diagnosticFormat)
}

interface Helping {
    fun help(): TypstCompilerResult<String>
}

interface Update : Helping {
    fun revert(): TypstCompilerResult<String>
}

//fun main() {
//    println(
//        TPattern(
//            size = TArray(30.pt, 30.pt),
//            body = TSequence(
//                TPlace(body = TLine(start = TArray(0.pc, 0.pc), end = TArray(100.pc, 100.pc))),
//                TPlace(body = TLine(start = TArray(0.pc, 100.pc), end = TArray(100.pc, 0.pc))),
//            )
//        ).repr()
//    )
//
//    val typst = Typst("./typst")
//
//    val x = typst.query<TValue>(
//        "src/test/resources/org/ldemetrios/typst4k/test.typ",
//        "full",
//    ).orElseThrow().value.single()
//    println(x.repr())
//}
