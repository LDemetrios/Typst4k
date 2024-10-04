package org.ldemetrios.typst4k.dsl

import org.ldemetrios.utilities.exec
import java.io.Reader

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

fun Typst(executable: String = "typst", forQueries: String = executable, func: CommonConfig.() -> Unit): Typst {
    val config = CommonConfig().also(func)
    return Typst(executable, forQueries, config)
}

data class Typst(
    val executable: String = "typst",
    val forQueries: String = executable,
    val config: CommonConfig = CommonConfig()
) {
    inline fun withConfig(block: CommonConfig.() -> Unit): Typst = copy(config = config.copy().apply(block))

    @PublishedApi
    internal fun execute(bin: String, stdin: Reader?, arguments: Command): TypstCompilerResult<String> {
        val (out, err, exit) = exec(listOf(bin) + arguments.entries, stdin)
        return TypstCompilerResult(exit == 0, out.joinToString(System.lineSeparator()), parseTypstStacktrace(err, AssertionError().stackTrace.asList()))
    }

    @PublishedApi
    internal fun execute(bin: String, vararg arguments: String): TypstCompilerResult<String> {
        val (out, err, exit) = exec(listOf(bin) + arguments)
        return TypstCompilerResult(exit == 0, out.joinToString(System.lineSeparator()), parseTypstStacktrace(err, AssertionError().stackTrace.asList()))
    }

    fun help() = execute(executable,"help")

    fun update(version: String? = null, forceDowngrade: Boolean = false) =
        execute(
            executable,
            null,
            Command("update")
                .optionalKey("--force", forceDowngrade)
                .optionalArg(version)
        )

    val update = object : Update {

        override fun revert() = execute(executable,"update", "--revert")

        override fun help() = execute(executable,"update", "--help")
    }

    val query = object : Helping {
        override fun help() = execute(executable,"query", "--help")
    }
}

interface Helping {
    fun help(): TypstCompilerResult<String>
}

interface Update : Helping {
    fun revert(): TypstCompilerResult<String>
}
