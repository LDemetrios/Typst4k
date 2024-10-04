package org.ldemetrios.typst4k.dsl

import java.io.PrintStream

data class TypstCompilerResult<E : Any> internal constructor(
    val success: Boolean,
    val result: E?,
    val trace: TypstStacktrace
) {
    fun <R : Any> map(func: (E) -> R?) = TypstCompilerResult(success, result?.let { func(it) }, trace)
    fun orElse(func: () -> E) = result ?: func
    fun orElseThrow() = result ?: throw TypstCompilerException(trace)
}

enum class ErrorSeverity {
    INFO, WARNING, ERROR
}

class TypstStacktrace(val entries: List<TraceEntry>, val back : List<StackTraceElement>?) {
    fun format(): String {
        val sb = StringBuilder()
        for (entry in entries) {
            sb.append("  at ${entry.file}:${entry.startLine + 1}:${entry.startColumn + 1}...${entry.endLine + 1}:${entry.endColumn + 1}\n")
        }
        return sb.toString()
    }

    fun print(pw: PrintStream = System.err) {
        TypstCompilerException(this).printStackTrace(pw)
    }
}

data class TraceEntry(
    val severity: ErrorSeverity,
    val file: String?,
    val startLine: Int,
    val startColumn: Int,
    val endLine: Int,
    val endColumn: Int,
    val message: String,
    val hints: List<String>,
)

fun String.severity(): ErrorSeverity? = when {
    this.startsWith("error") -> ErrorSeverity.ERROR
    this.startsWith("warning") -> ErrorSeverity.WARNING
    this.startsWith("help") -> ErrorSeverity.INFO
    else -> null
}

fun parseTypstStacktrace(log: List<String>, back: List<StackTraceElement>): TypstStacktrace {
    val starts = mutableListOf<Int>()
    for ((i, line) in log.withIndex()) {
        val severity = line.severity()
        if (severity != null) starts.add(i)
    }
    starts.add(log.size)
    val chunks: List<List<String>> = (0 until starts.size - 1).map { i ->
        log.subList(starts[i], starts[i + 1])
    }
        .map { it.filter(String::isNotBlank) }
        .filter { it.isNotEmpty() }

    if (chunks.isEmpty()) return TypstStacktrace(emptyList(), back)


    return TypstStacktrace(chunks.map(::parseTraceEntry), back)
}

fun parseTraceEntry(part: List<String>): TraceEntry {
    val (severity, message) = part[0].split(":", limit = 2).map { it.trim() }

    val hints = part
        .takeLastWhile { Regex(" *= *hint:(.*)").matches(it) }
        .map { Regex(" *= *hint:(.*)").matchEntire(it)!!.groupValues[1].trim() }

    val body = part.subList(
        1,  part.size - hints.size
    )
        .filter {! it.matches(Regex(" *│ *")) }

    if (body.isEmpty())         return TraceEntry(
            parseSeverity(severity),
            null,
            -1, -1, -1, -1,
            message,
            hints
        )

    val fileFragmentHeader = body[0].dropWhile { it in " ┌─" }
        .reversed().split(":", limit = 3).map { it.reversed() }.reversed()

    if (fileFragmentHeader.size == 3) {
        val (file, line, char) = fileFragmentHeader

        val last = Regex(" *[\\│|] ( *\\^*) *").matchEntire(body.last())
        if (/*oneline*/ last != null) {
            val endColumn = last.groupValues[1].length
            return TraceEntry(
                severity = parseSeverity(severity),
                file = file,
                startLine = line.toInt() - 1,
                startColumn = char.toInt(),
                endLine = line.toInt() - 1,
                endColumn = endColumn,
                message,
                hints
            )
        } else {
            val penultimate = body[body.size - 2]
            val last = Regex(" *[\\│|] ╰─(─*\\^)").matchEntire(body.last())
            return TraceEntry(
                severity = parseSeverity(severity),
                file = file,
                startLine = line.toInt() - 1,
                startColumn = char.toInt(),
                endLine = penultimate.split(" ").filter { it.isNotBlank() }[0].toInt() - 1,
                endColumn = last!!.groupValues[1].length,
                message,
                hints
            )
        }
    } else {
        require(severity == "warning") { "$severity, but $fileFragmentHeader" }
        return TraceEntry(
            ErrorSeverity.WARNING,
            null,
            -1, -1, -1, -1,
            message + part.drop(1).joinToString(System.lineSeparator()),
            hints
        )
    }
}

fun parseSeverity(severity: String): ErrorSeverity {
    return when (severity) {
        "help" -> ErrorSeverity.INFO
        "warning" -> ErrorSeverity.WARNING
        "error" -> ErrorSeverity.ERROR
        else -> throw AssertionError(severity)
    }
}

