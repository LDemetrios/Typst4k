package org.ldemetrios.typst4k.orm

import java.awt.Color


data class TColorOklab(val lightness: TRatio, val a: TFloatOrRatio, val b: TFloatOrRatio, val alpha: TRatio? = null) :
    TColor {
    override fun toTypstRepr(): String {
        return scriptingRepr(
            "oklab",
            listOf(
                null to lightness,
                null to a,
                null to b,
                null to alpha,
            )
        )
    }
}

data class TColorOklch(val lightness: TRatio, val chroma: TFloatOrRatio, val hue: TAngle, val alpha: TRatio? = null) :
    TColor {
    override fun toTypstRepr(): String {
        return scriptingRepr(
            "oklch",
            listOf(
                null to lightness,
                null to chroma,
                null to hue,
                null to alpha,
            )
        )
    }
}

data class TColorLinearRGB(
    val red: TIntOrRatio,
    val green: TIntOrRatio,
    val blue: TIntOrRatio,
    val alpha: TIntOrRatio? = null
) : TColor {
    override fun toTypstRepr(): String {
        return scriptingRepr(
            "color.linear-rgb",
            listOf(
                null to red,
                null to green,
                null to blue,
                null to alpha,
            )
        )
    }
}


private fun dehex(x: String): List<Long> {
    val x = if (x.startsWith("#")) x.substring(1) else x
    return when (x.length) {
        3, 4 -> x.map { it.toString().toInt(16) * 16L }
        6, 8 -> x.chunked(2).map { it.toLong(16) }
        else -> throw IllegalArgumentException("Invalid hex string $x")
    }
}

data class TColorRGB(
    val red: TIntOrRatio,
    val green: TIntOrRatio,
    val blue: TIntOrRatio,
    val alpha: TIntOrRatio? = null
) : TColor {
    constructor(hex: TStr) : this(
        TInt(dehex(hex.value)[0]),
        TInt(dehex(hex.value)[1]),
        TInt(dehex(hex.value)[2]),
        dehex(hex.value).getOrNull(3)?.let { TInt(it) }
    )

    override fun toTypstRepr(): String {
        return scriptingRepr(
            "rgb",
            listOf(
                null to red,
                null to green,
                null to blue,
                null to alpha,
            )
        )
    }
}

data class TColorHSL(
    val hue: TAngle,
    val saturation: TRatio,
    val lightness: TRatio,
    val alpha: TRatio? = null
) : TColor {
    override fun toTypstRepr(): String {
        return scriptingRepr(
            "color.hsl",
            listOf(
                null to hue,
                null to saturation,
                null to lightness,
                null to alpha,
            )
        )
    }
}

data class TColorHSV(
    val hue: TAngle,
    val saturation: TIntOrRatio,
    val value: TIntOrRatio,
    val alpha: TIntOrRatio? = null
) : TColor {
    override fun toTypstRepr(): String {
        return scriptingRepr(
            "color.hsv",
            listOf(
                null to hue,
                null to saturation,
                null to value,
                null to alpha,
            )
        )
    }
}

data class TColorCMYK(
    val cyan: TRatio,
    val magenta: TRatio,
    val yellow: TRatio,
    val black: TRatio,
    val alpha: TRatio? = null
) : TColor {
    override fun toTypstRepr(): String {
        return scriptingRepr(
            "cmyk",
            listOf(
                null to cyan,
                null to magenta,
                null to yellow,
                null to black,
                null to alpha,
            )
        )
    }
}

data class TColorLuma(
    val lightness: TIntOrRatio,
) : TColor {
    override fun toTypstRepr(): String {
        return scriptingRepr(
            "luma",
            listOf(
                null to lightness,
            )
        )
    }
}

val Color.typst get() = TColorRGB(TInt(red.toLong()), TInt(green.toLong()), TInt(blue.toLong()), TInt(alpha.toLong()))

