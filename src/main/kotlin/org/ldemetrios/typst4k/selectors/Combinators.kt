package org.ldemetrios.typst4k.selectors

import org.ldemetrios.typst4k.orm.TypstValue

data class OrSelector<T : TypstValue>(val a: Selector<T>, val b: Selector<T>) : Selector<T> {
    override fun toString() = "$a.or($b)"
}

fun <T : TypstValue> Selector<T>.or(b: Selector<T>) = OrSelector(this, b)

data class AndSelector<T : TypstValue>(val a: Selector<T>, val b: Selector<T>) : Selector<T> {
    override fun toString() = "$a.and($b)"
}

fun <T : TypstValue> Selector<T>.and(b: Selector<T>) = AndSelector(this, b)

data class LabelSelector(val label: String) : Selector<TypstValue> {
    override fun toString() = "<$label>"
}

fun <T : TypstValue> Selector<T>.or(label: String) = OrSelector(this, LabelSelector(label)) as Selector<T>
fun <T : TypstValue> Selector<T>.and(label: String) = AndSelector(this, LabelSelector(label)) as Selector<T>

