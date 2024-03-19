package org.ldemetrios.typst4k

import kotlinx.serialization.json.Json
import org.ldemetrios.typst4k.orm.TArray
import org.ldemetrios.typst4k.orm.TInt
import org.ldemetrios.typst4k.orm.TMetadata
import org.ldemetrios.typst4k.orm.TValue
import org.ldemetrios.typst4k.rt.deserializeTypstValue
import org.ldemetrios.typst4k.selectors.Selector
import org.ldemetrios.utilities.invoke
import java.nio.file.Path

val FROM_STDIN: Path? = null

data class Typst(val executable: String) {
    @PublishedApi
    internal val json = Json {
        ignoreUnknownKeys = true
        classDiscriminator = "func"
    }

    fun help() {
        executable("help")
    }

    val update = object : Update {
        override fun revert() {
            executable("update", "--revert")
        }

        override operator fun invoke(version: String?, forceDowngrade: Boolean) {
            if (version == null) {
                if (forceDowngrade) {
                    executable("update", "--force")
                } else {
                    executable("update")
                }
            } else {
                if (forceDowngrade) {
                    executable("update", "--force", version)
                } else {
                    executable("update", version)
                }
            }
        }

        override fun help() {
            executable("update", "--help")
        }
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

    val compile = object : Compile {
        override fun help() {
            executable("compile", "--help")
        }

        override operator fun invoke(
            input: Path?,
            output: Path?,
            root: Path?,
            fontPath: Path?,
            diagnosticFormat: DiagnosticFormat,
            format: String?,
            open: Boolean,
            ppi: Int?,  /*= 144*/
            timings: Path?,
        ) {
            val list = mutableListOf("compile")

            if (root != null) {
                list.add("--root")
                list.add(root.toString())
            }

            if (fontPath != null) {
                list.add("--font-path")
                list.add(fontPath.toString())
            }

            list.add("--diagnostic-format")
            list.add(diagnosticFormat.toString())

            if (format != null) {
                list.add("--format")
                list.add(format)
            }

            if (open) {
                list.add("--open")
                list.add("true")
            }

            if (ppi != null) {
                list.add("--ppi")
                list.add(ppi.toString())
            }

            if (timings != null) {
                list.add("--timings")
                list.add(timings.toString())
            }

            if (input != null) {
                list.add(input.toString())
            } else {
                list.add("-")
            }

            if (output != null) {
                list.add(output.toString())
            }

//            --input <key=value>
//                    Add a string key-value pair visible through `sys.inputs`

            executable(*list.toTypedArray())
        }
    }

    inner class Queryer internal constructor(
        @PublishedApi
        internal val params: Array<String>
    ) {
        //TODO
        operator fun get(selector: String): List<TValue> { //by label!
            return json.decodeFromString<List<TValue>>(
                executable(*params, "<$selector>").joinToString("\n")
            )
        }

        inline fun <reified E : TValue> getAs(selector: String): List<E> { //by label!
            return json.decodeFromString<List<E>>(
                executable(*params, "<$selector>").joinToString("\n")
            )
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Queryer

            return params.contentEquals(other.params)
        }

        override fun hashCode(): Int {
            return params.contentHashCode()
        }
    }

    val query = object : Query {
        override fun inferParamList(
            input: Path?,
            root: Path?,
            fontPath: Path?,
            diagnosticFormat: DiagnosticFormat,
        ): Array<String> {
            val list = mutableListOf("query")

            if (root != null) {
                list.add("--root")
                list.add(root.toString())
            }

            if (fontPath != null) {
                list.add("--font-path")
                list.add(fontPath.toString())
            }

            list.add("--diagnostic-format")
            list.add(diagnosticFormat.toString())

            if (input != null) {
                list.add(input.toString())
            } else {
                list.add("-")
            }
            return list.toTypedArray()
        }

        override fun help() {
            executable("query", "--help")
        }
    }

    inline fun <reified T : TValue> query(
        input: Path?,
        selector: String,
        root: Path? = null,
        fontPath: Path? = null,
        diagnosticFormat: DiagnosticFormat = DiagnosticFormat.HUMAN
    ): TArray<T> {
        val list = query.inferParamList(input, root, fontPath, diagnosticFormat)

        val result = executable(*list, selector).joinToString("\n")

        return deserializeTypstValue<TArray<T>>(result)
    }

    inline fun <reified T : TValue> query(
        input: Path?,
        selector: Selector<T>,
        root: Path? = null,
        fontPath: Path? = null,
        diagnosticFormat: DiagnosticFormat = DiagnosticFormat.HUMAN
    ): TArray<T> {
        val list = query.inferParamList(input, root, fontPath, diagnosticFormat)

        val result = executable(*list, selector.toString()).joinToString("\n")

        return deserializeTypstValue<TArray<T>>(result)
    }
}

interface Query {
    fun inferParamList(
        input: Path?,
        root: Path? = null,
        fontPath: Path? = null,
        diagnosticFormat: Typst.DiagnosticFormat = Typst.DiagnosticFormat.HUMAN,
    ): Array<String>

    fun help()
}

interface Compile {
    fun help()

    operator fun invoke(
        input: Path?,
        output: Path? = null,
        root: Path? = null,
        fontPath: Path? = null,
        diagnosticFormat: Typst.DiagnosticFormat = Typst.DiagnosticFormat.HUMAN,
        format: String? = null,
        open: Boolean = false,
        ppi: Int? = null, /*= 144*/
        timings: Path? = null,
    )
}

interface Update {
    fun revert()
    operator fun invoke(version: String? = null, forceDowngrade: Boolean = false)
    fun help()
}

fun main() {
    val typst = Typst("/home/ldemetrios/Workspace/typst-no-dynamic-values/target/release/typst")

    val x = typst.query<TMetadata<TArray<TInt>>>(
        Path.of("test.typ"),
        "<sss>",
    )
    val y = x[0].value[1]
}
