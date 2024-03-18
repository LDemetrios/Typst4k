package org.ldemetrios.typst4k.selectors

import org.ldemetrios.typst4k.orm.TValue

data class OrSelector<T : TValue>(val a: Selector<T>, val b: Selector<T>) : Selector<T> {
    override fun toString() = "$a.or($b)"
}

fun <T : TValue> Selector<T>.or(b: Selector<T>) = OrSelector(this, b)

data class AndSelector<T : TValue>(val a: Selector<T>, val b: Selector<T>) : Selector<T> {
    override fun toString() = "$a.and($b)"
}

fun <T : TValue> Selector<T>.and(b: Selector<T>) = AndSelector(this, b)

data class LabelSelector(val label: String) : Selector<TValue> {
    override fun toString() = "<$label>"
}

fun <T : TValue> Selector<T>.or(label: String) = OrSelector(this, LabelSelector(label)) as Selector<T>
fun <T : TValue> Selector<T>.and(label: String) = AndSelector(this, LabelSelector(label)) as Selector<T>

