package org.ldemetrios.typst4k

import kotlinx.serialization.json.Json
import org.ldemetrios.typst4k.TElement
import org.ldemetrios.utilities.invoke
import java.nio.file.Path

val FROM_STDIN: Path? = null

object Typst {

    @PublishedApi
    internal val json = Json {
        ignoreUnknownKeys = true
        classDiscriminator = "func"
    }

    fun help() {
        "typst"("help")
    }

    object update {
        fun revert() {
            "typst"("update", "--revert")
        }

        operator fun invoke(version: String? = null, forceDowngrade: Boolean = false) {
            if (version == null) {
                if (forceDowngrade) {
                    "typst"("update", "--force")
                } else {
                    "typst"("update")
                }
            } else {
                if (forceDowngrade) {
                    "typst"("update", "--force", version)
                } else {
                    "typst"("update", version)
                }
            }
        }

        fun help() {
            "typst"("update", "--help")
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

    object compile {
        fun help() {
            "typst"("compile", "--help")
        }

        operator fun invoke(
            input: Path?,
            output: Path? = null,
            root: Path? = null,
            fontPath: Path? = null,
            diagnosticFormat: Typst.DiagnosticFormat = DiagnosticFormat.HUMAN,
            format: String? = null,
            open: Boolean = false,
            ppi: Int? = null, /*= 144*/
            timings: Path? = null,
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

            "typst"(*list.toTypedArray())
        }
    }

    data class Queryer internal constructor(
        @PublishedApi
        internal val params: Array<String>
    ) {
        //TODO
        operator fun get(selector: String): List<TElement> { //by label!
            return json.decodeFromString<List<TElement>>(
                "typst"(*params, "<$selector>").joinToString("\n")
            )
        }

        inline fun <reified E : TElement> getAs(selector: String): List<E> { //by label!
            return json.decodeFromString<List<E>>(
                "typst"(*params, "<$selector>").joinToString("\n")
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

    object query {
        fun help() {
            "typst"("query", "--help")
        }

        operator fun invoke(
            input: Path?,
            root: Path? = null,
            fontPath: Path? = null,
            diagnosticFormat: Typst.DiagnosticFormat = DiagnosticFormat.HUMAN,
        ): Queryer {
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

            return Queryer(list.toTypedArray());
        }
    }
}