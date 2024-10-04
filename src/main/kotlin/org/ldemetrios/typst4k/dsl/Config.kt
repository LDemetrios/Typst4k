package org.ldemetrios.typst4k.dsl

import org.ldemetrios.typst4k.dsl.Typst
import java.nio.file.Path

enum class OutputFormat {
    PDF, PNG, SVG;

    override fun toString(): String = this.name.lowercase()
}

enum class SerialFormat {
    JSON, YAML;

    override fun toString(): String = this.name.lowercase()
}

data class CompileConfig(
    var root: Path? = null,
    var inputs: MutableMap<String, String> = mutableMapOf(),
    var fontPath: Path? = null,
    var format: OutputFormat? = null,
    var open: Boolean? = null,
    var ppi: Int? = null,
    var timings: Path? = null,
)

data class QueryConfig(
    var root: Path? = null,
    var inputs: MutableMap<String, String> = mutableMapOf(),
    var fontPath: Path? = null,
    var format: SerialFormat? = null,
)

data class WatchConfig(
    var root: Path? = null,
    var inputs: MutableMap<String, String> = mutableMapOf(),
    var fontPath: Path? = null,
    var format: OutputFormat? = null,
    var open: Boolean? = null,
    var ppi: Int? = null,
    var timings: Path? = null,
)

data class CommonConfig(
    var root: Path? = null,
    var inputs: MutableMap<String, String> = mutableMapOf(),
    var fontPath: Path? = null,
    var ppi: Int? = null,
    var format: OutputFormat = OutputFormat.PDF,
    internal val compileConfig: CompileConfig = CompileConfig(),
    internal val queryConfig: QueryConfig = QueryConfig(),
    internal val watchConfig: WatchConfig = WatchConfig(),
)

fun CommonConfig.input(key: String, value: String) = inputs.put(key, value)
fun WatchConfig.input(key: String, value: String) = inputs.put(key, value)
fun QueryConfig.input(key: String, value: String) = inputs.put(key, value)
fun CompileConfig.input(key: String, value: String) = inputs.put(key, value)

fun CommonConfig.compile(func: CompileConfig.() -> Unit) = compileConfig.func()
fun CommonConfig.query(func: QueryConfig.() -> Unit) = queryConfig.func()
fun CommonConfig.watch(func: WatchConfig.() -> Unit) = watchConfig.func()

