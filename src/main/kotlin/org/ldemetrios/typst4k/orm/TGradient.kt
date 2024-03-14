package org.ldemetrios.typst4k.orm

data class TGradientLinear(
    val stops: TArray<TColor>,
    val space: TStr? = null,
    val relative: TAutoOrStr? = null,
    val dir: TDirection? = null,
    val angle: TAngle? = null,
) : TGradient {
    override fun toTypstRepr(): String {
        return scriptingRepr(
            "gradient.linear", listOf(
                null to stops,
                "space" to space,
                "relative" to relative,
                null to dir,
                "angle" to angle,
            )
        )
    }
}

data class TGradientRadial(
    val stops: TArray<TColor>,
    val space: TStr? = null,
    val relative: TAutoOrStr? = null,
    val center: TArray<TRatio>? = null,
    val radius: TRatio? = null,
    val focalCenter: TAutoOrArray<TRatio>? = null,
    val focalRadius: TRatio? = null,
) : TGradient {
    override fun toTypstRepr(): String {
        return scriptingRepr(
            "gradient.linear", listOf(
                null to stops,
                "space" to space,
                "relative" to relative,
                "center" to center,
                "radius" to radius,
                "focal-center" to focalCenter,
                "focal-radius" to focalRadius,
            )
        )
    }
}

data class TGradientConic(
    val stops: TArray<TColor>,
    val angle: TAngle? = null,
    val space: TStr? = null,
    val relative: TAutoOrStr? = null,
    val center: TArray<TRatio>? = null,
) : TGradient {
    override fun toTypstRepr(): String {
        return scriptingRepr(
            "gradient.linear", listOf(
                null to stops,
                "angle" to angle,
                "space" to space,
                "relative" to relative,
                "center" to center,
            )
        )
    }
}

data class TGradientSharp(
    val self: TGradient,
    val steps: TInt,
    val smoothness: TRatio? = null,
) : TGradient {
    override fun toTypstRepr(): String {
        return "self.sharp($steps" + (smoothness?.let { ", " + it.toTypstRepr() } ?: "") + ")"
    }
}

data class TGradientRepeat(
    val self: TGradient,
    val repetitions: TInt,
    val mirror: TBool? = null,
) : TGradient {
    override fun toTypstRepr(): String {
        return "self.sharp($repetitions" + (mirror?.let { ", " + it.toTypstRepr() } ?: "") + ")"
    }
}

