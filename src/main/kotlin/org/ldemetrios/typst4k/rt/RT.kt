package org.ldemetrios.typst4k.rt

import org.ldemetrios.typst4k.orm.*
import org.ldemetrios.typst4k.orm.nongeneric.NGTArguments
import org.ldemetrios.typst4k.orm.nongeneric.NGTMetadata
import org.ldemetrios.typst4k.orm.nongeneric.NGTValue
import org.ldemetrios.utilities.cast

typealias CommonInterfaceName = TValue

fun main() {
    println("Compiles")
}

object RT {
    fun <E : CommonInterfaceName> reprOf(value: List<E>): String =
        "( " + value.joinToString("") { it.repr() + ", " } + ")"

    fun <V : CommonInterfaceName> reprOf(value: Map<String, V>): String =
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

    fun <A : CommonInterfaceName> reprOf(value: TArguments<A>): String = "( " +
            (value.named.map { reprOf(it.key) + " : " + it.value.repr() } +
                    value.positional.map { it.repr() }).joinToString(", ") + ")"

    fun reprOf(value: TSpace): String = "[ ]"
    fun reprOf(value: TSequence): String {
        return "{ " + value.children.joinToString("; ") { it.repr() } + "; }"
    }

    fun structRepr(
        name: String,
        vararg elements: Pair<String?, CommonInterfaceName?>,
    ): String {
        if (elements.isEmpty()) return name
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
                    sb.append(", ")
                }
            } else {
                if (element.second == null) {
                    // skip???
                } else {
                    sb.append(element.second!!.repr())
                    sb.append(", ")
                }
            }
        }
        return sb.append(")").toString()
    }

    fun reprOf(value: TAlignment): String = listOf(value.x, value.y).filterNotNull().joinToString(" + ")
    fun <T : TValue> convertArray(value: List<NGTValue>) = TArray(value.map { it.convert() as T })
    fun <T : TValue> convertArguments(value: NGTArguments<T>) =
        TArguments<T>(value.positional.convert().cast(), value.named.convert().cast())

    fun <T : TValue> convertDictionary(value: Map<String, NGTValue>): TDictionary<T> =
        TDictionary<T>(value.mapValues { it.value.convert() as T })

    fun <T : TValue> convertMetadata(value: NGTMetadata<T>) =
        TMetadata<T>(value.value.convert() as T)
}