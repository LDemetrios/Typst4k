package org.ldemetrios.typst4k.rt

import org.ldemetrios.typst4k.orm.*

val Float.t get() = TFloat(this.toDouble())
val Double.t get() = TFloat(this)
val Byte.t get() = TInt(this.toLong())
val Short.t get() = TInt(this.toLong())
val Int.t get() = TInt(this.toLong())
val Long.t get() = TInt(this)
val String.t get() = TStr(this)
val Boolean.t get() = TBool(this)

val TFloat.pt get() = TLength(this, 0f.t)
val TFloat.em get() = TLength(0f.t, this)
val TInt.pt get() = TLength(value.toDouble().t, 0f.t)
val TInt.em get() = TLength(0f.t, value.toDouble().t)

val TFloat.fr get() = TFraction(this)
val TInt.fr get() = TFraction(value.toDouble().t)

val TFloat.pc get() = TRatio((value / 100).t)
val TInt.pc get() = TRatio((value / 100.0).t)

val TFloat.deg get() = TAngle(this)
val TInt.deg get() = TAngle(value.toDouble().t)
val TFloat.rad get() = TAngle((this.value * (180 / Math.PI)).t)
val TInt.rad get() = TAngle(((this.value * (180 / Math.PI))).t)

