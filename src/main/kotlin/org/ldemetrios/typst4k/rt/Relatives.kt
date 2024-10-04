package org.ldemetrios.typst4k.rt

import org.ldemetrios.typst4k.orm.TLength
import org.ldemetrios.typst4k.orm.TRatio
import org.ldemetrios.typst4k.orm.TRelative
import org.ldemetrios.typst4k.orm.TRelativeImpl

val TRelative.abs: TLength?    get() = when(this) {
    is TRatio -> 0.pt
    is TLength -> this
    is TRelativeImpl -> this.abs
    else -> throw AssertionError()
}

val TRelative.rel : TRatio? get() = when(this) {
    is TRatio -> this
    is TLength -> 0.pc
    is TRelativeImpl -> this.rel
    else -> throw AssertionError()
}
