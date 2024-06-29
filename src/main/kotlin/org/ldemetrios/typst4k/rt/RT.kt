package org.ldemetrios.typst4k.rt

import org.ldemetrios.typst4k.orm.*

typealias CommonInterfaceName = TValue

object RT {
    fun <E : CommonInterfaceName> reprOf(value: List<E>): String = when (value.size) {
        0 -> "()"
        1 -> "(" + value[0].repr() + ",)"
        else -> "( " + value.joinToString(", ") { it.repr() } + ")"
    }


    fun <V : CommonInterfaceName> reprOf(value: Map<String, V>): String = when (value.size) {
        0 -> "(:)"
        else -> "( " + value.entries.joinToString(", ") { reprOf(it.key) + " : " + it.value.repr() } + ")"
    }

    fun reprOf(value: String): String = "\"" +
            value.replace("\\", "\\\\")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t")
                .replace("\"", "\\\"") + "\""

    fun reprOf(value: Boolean): String = value.toString()
    fun reprOf(value: Long): String = value.toString()
    fun reprOf(value: Double): String = value.toString()

    fun <A : CommonInterfaceName> reprOf(value: TArguments<A>): String = "((..args) => args)(" +
            (value.named.value.map { it.key + " : " + it.value.repr() } +
                    value.positional.value.map { it.repr() }).joinToString(", ") + ")"

    fun reprOf(value: TSpace): String = "[ ]"
    fun reprOf(value: TSequence): String {
        return "{ " + value.children.value.joinToString("; ") { it.repr() } + "; }"
    }

    fun structRepr(
        name: String,
        vararg elements: Triple<Boolean, String?, TValue?>, // vararg, name (null if positional), value
    ): String {
        if (name == "none" || name == "auto") return name
        if (name == "text") {
            val present = elements.filter { it.third != null }
            if (present.size == 1 && !present[0].first && present[0].second == null && present[0].third is TStr) {
                return present[0].third!!.repr()
            }
        }

        val sb = StringBuilder(name)
        sb.append("(")
        val entries = mutableListOf<String>()
        for (element in elements) {
            if (!element.first) {
                if (element.second != null) {
                    if (element.third == null) {
                        // skip
                    } else {
                        entries.add(element.second + ": " + element.third!!.repr())
                    }
                } else {
                    if (element.third == null) {
                        // skip???
                    } else {
                        entries.add(element.third!!.repr())
                    }
                }
            } else {
                if (element.third == null) {
                    // skip???
                } else {
                    entries.add(".." + element.third!!.repr())
                }
            }
        }
        return name + "(" + entries.joinToString(", ") + ")"
    }

    private fun sumOfNotNull(vararg values: String?) = listOfNotNull(*values).run {
        if (isEmpty()) "0" else joinToString(" + ")
    }

    fun reprOf(value: TAlignment): String = sumOfNotNull(value.horizontal?.value, value.vertical?.value)

    fun reprOf(value: TAngle): String = "${value.deg}deg"
    fun reprOf(value: TFraction): String = "${value.value}fr"
    fun reprOf(value: TRatio): String = "${value.value.value * 100}%"
    fun reprOf(value: TLength): String = sumOfNotNull(value.em?.let { "${it}em" }, value.pt?.let { "${it}pt" })

    fun reprOf(value: TRelative): String =
        listOfNotNull(
            value.abs?.em?.let { "${it}em" },
            value.abs?.pt?.let { "${it}pt" },
            value.rel?.repr()
        ).joinToString(" + ")

    fun reprOf(value: TPattern): String = structRepr(
        "pattern",
        Triple(false, "size", value.size),
        Triple(false, "spacing", value.spacing),
        Triple(false, "relative", value.relative),
        Triple(false, null, value.body ?: TSpace),
    )

    fun reprOf(value: TCounter): String = when (value.value) {
        is TPageCounterKey -> "counter(page)"
        is TStrCounterKey -> "counter(${value.value.str.repr()})"
        is TSelectorCounterKey -> "counter(${value.value.selector.repr()})"
        else -> throw AssertionError()
    }

    fun reprOf(value: TRegexSelector): String = "selector(${value.regex.repr()})"

    fun reprOf(value: TLabelSelector): String = "selector(${value.label.repr()})"


    fun reprOf(value: TElementSelector): String =
        if (value.where == null) value.element.value
        else value.element.value +
                ".where(" +
                value.where.value.map { it.key + " : " + it.value.repr() }.joinToString(", ") +
                ")"

    fun reprOf(value: TAfterSelector): String {
        return value.repr() +
                ".after(" +
                value.start.repr() +
                (if (value.inclusive != null) ", inclusive: ${value.inclusive.repr()}" else "") +
                ")"
    }

    fun reprOf(value: TBeforeSelector): String {
        return value.repr() +
                ".before(" +
                value.end.repr() +
                (if (value.inclusive != null) ", inclusive: ${value.inclusive.repr()}" else "") +
                ")"
    }

    fun reprOf(value: TAndSelector): String = when (value.variants.size) {
        0 -> throw IllegalArgumentException()
        1 -> value.variants[0].repr()
        else -> value.variants[0].repr() + ".and(" +
                value.variants.drop(1).joinToString(", ") { it.repr() } + ")"
    }

    fun reprOf(value: TOrSelector): String = when (value.variants.size) {
        0 -> throw IllegalArgumentException()
        1 -> value.variants[0].repr()
        else -> value.variants[0].repr() + ".or(" +
                value.variants.drop(1).joinToString(", ") { it.repr() } + ")"
    }

    fun reprOf(value: TType): String = value.name.value
    fun reprOf(value: TModule): String = value.name.value
    fun reprOf(value: TDirection): String = value.value.value
}