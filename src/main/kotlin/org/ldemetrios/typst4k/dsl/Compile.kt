package org.ldemetrios.typst4k.dsl

import org.ldemetrios.typst4k.withTmp
import java.io.Reader
import java.io.StringReader
import java.nio.file.Path
import kotlin.io.path.pathString

private fun CommonConfig.composeCompileArgs() =
    Command("compile")
        .optionalEntry("--root", compileConfig.root ?: root)
        .multiple("--input", (inputs + compileConfig.inputs).map { it.key + "=" + it.value })
        .optionalEntry("--font-path", compileConfig.fontPath ?: fontPath)
        .optionalEntry("--format", compileConfig.format ?: format)
        .optionalEntry("--open", compileConfig.open)
        .optionalEntry("--ppi", compileConfig.ppi ?: ppi)
        .optionalEntry("--timings", compileConfig.timings)


@Suppress("RedundantUnitExpression")
private fun Typst.compile(
    stdin: Reader?,
    input: String,
    output: String?,
    configer: (CompileConfig.() -> Unit)? = null,
): TypstCompilerResult<Unit> {
    val config = if (configer == null) this.config else {
        val comp = CompileConfig()
        comp.configer()
        this.config.copy(compileConfig = comp)
    }

    val res = execute(
        executable,stdin,
        config.composeCompileArgs()
            .optionalArg(input)
            .optionalArg(output)
    )

    return res.map { Unit }
}

fun Typst.compile(
    input: Path, output: Path? = null, configer: (CompileConfig.() -> Unit)? = null,
): TypstCompilerResult<Unit> {
    return compile(null, input.pathString, output?.pathString, configer)
}

fun Typst.compile(
    input: String, output: Path, configer: (CompileConfig.() -> Unit)? = null,
): TypstCompilerResult<Unit> {
    return compile(StringReader(input), "-", output.pathString, configer)
}

fun Typst.compile(
    input: Reader, output: Path, configer: (CompileConfig.() -> Unit)? = null,
): TypstCompilerResult<Unit> {
    return compile(input, "-", output.pathString, configer)
}

fun Typst.compileAndRead(
    input: Path, configer: (CompileConfig.() -> Unit)? = null,
): TypstCompilerResult<List<String>> = withTmp(
    { compile(null, input.pathString, it.resolve("out-{n}").pathString, configer) },
    { res, text -> res.map { text } }
)

fun Typst.compileAndRead(
    input: String, configer: (CompileConfig.() -> Unit)? = null,
): TypstCompilerResult<List<String>> = withTmp(
    { compile(StringReader(input), "-", it.resolve("out-{n}").pathString, configer) },
    { res, text -> res.map { text } }
)

fun Typst.compileAndRead(
    input: Reader, configer: (CompileConfig.() -> Unit)? = null,
): TypstCompilerResult<List<String>> = withTmp(
    { compile(input, "-", it.resolve("out-{n}").pathString, configer) },
    { res, text -> res.map { text } }
)

