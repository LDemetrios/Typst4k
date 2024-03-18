package org.ldemetrios.typst4k.repr

import org.ldemetrios.typst4k.dom.*

object Representations {
    fun <E : Value> reprOf(value: List<E>): String = "( " + value.joinToString("") { it.repr() + ", " } + ")"
    fun <V : Value> reprOf(value: Map<String, V>): String =
        "( " + value.entries.joinToString("") { reprOf(it.key) + " : " + it.value.repr() + ", " } + ")"

    fun reprOf(value: String): String = "\"" +
            value.replace("\\", "\\\\")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t")
                .replace("\"", "\\\"") + "\""

    fun reprOf(value: Boolean): String = value.toString()
    fun reprOf(value: Long): String = value.toString()
    fun reprOf(value: Double): String = value.toString()

    fun <A : Value> reprOf(value: TArguments<A>): String = "( " +
            (value.named.map { reprOf(it.key) + " : " + it.value.repr() } +
                    value.positional.map { it.repr() }).joinToString(", ") + ")"

    fun reprOf(value: TSpace): String = "[ ]"
    fun reprOf(value: TSequence): String {
        return "{ " + value.children.joinToString("; ") { it.repr() } + "; }"
    }

    fun structRepr(
        name: String,
        vararg elements: Pair<String?, Value?>,
    ): String {
        val sb = StringBuilder(name)
        sb.append("(")
        for (element in elements) {
            if (element.first != null) {
                if (element.second == null) {
                    // skip
                } else {
                    sb.append(element.first)
                    sb.append(": ")
                    sb.append(element.second!!.repr())
                }
            } else {
                if (element.second == null) {
                    // skip???
                } else {
                    sb.append(element.second!!.repr())
                }
            }
        }
        return sb.append(")").toString()
    }

    fun reprOf(value: TAlignment): String = listOf(value.x, value.y).filterNotNull().joinToString(" + ")
}