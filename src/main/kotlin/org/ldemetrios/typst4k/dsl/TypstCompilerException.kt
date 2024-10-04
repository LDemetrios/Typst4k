package org.ldemetrios.typst4k.dsl

import java.io.File
import java.nio.file.Path

class TypstCompilerException(trace: TypstStacktrace) : Throwable(trace.entries.firstOrNull()?.message) {
    init {
        val own = (trace.back ?: stackTrace.asList()).asReversed().toMutableList()
        for (entry in trace.entries) {
            // String classLoaderName, String moduleName, String moduleVersion, String declaringClass, String methodName, String fileName, int lineNumber
            if (entry.file?.startsWith("@") == true) {
                val (namespace, pack, file) = entry.file.substring(1).split("/", limit = 3)
                val (packName, version) = pack.split(":")
                own.add(
                    StackTraceElement(
                        namespace,
                        packName,
                        version,
                        file.removeSuffix(".typ"),
                        "typ",
                        file.split(File.separator).last(),
                        entry.startLine + 1
                    )
                )
            } else {
                own.add(
                    StackTraceElement(
                        "org.ldemetrios.Typst4K",
                        null,
                        null,
                        entry.file?.removeSuffix(".typ") ?: "unknown-location",
                        "typ",
                        entry.file?.split(File.separator)?.last(),
                        entry.startLine + 1
                    )
                )
            }
        }
        this.stackTrace = own.asReversed().toTypedArray()
    }
}

fun main() {
    val result = Typst().compile(Path.of("dir/test.typ"))
    throw TypstCompilerException(
        result.trace,
    )
}