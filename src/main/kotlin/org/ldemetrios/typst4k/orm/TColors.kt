package org.ldemetrios.typst4k.orm


data class TColorOklab(val lightness: TRatio, val a: TFloatOrRatio, val b: TFloatOrRatio, val alpha: TRatio? = null) :
    TColor

data class TColorOklch(val lightness: TRatio, val chroma: TFloatOrRatio, val hue: TAngle, val alpha: TRatio? = null) :
    TColor

data class TColorLinearRGB(
    val red: TIntOrRatio,
    val green: TIntOrRatio,
    val blue: TIntOrRatio,
    val alpha: TIntOrRatio? = null
) : TColor


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
}

data class TColorHSL(
    val hue: TAngle,
    val saturation: TRatio,
    val lightness: TRatio,
    val alpha: TRatio? = null
) : TColor

data class TColorHSV(
    val hue: TAngle,
    val saturation: TIntOrRatio,
    val value: TIntOrRatio,
    val alpha: TIntOrRatio? = null
) : TColor

data class TColorCMYK(
    val cyan: TRatio,
    val magenta: TRatio,
    val yellow: TRatio,
    val black: TRatio,
    val alpha: TRatio? = null
) : TColor


data class TColorLuma(
    val lightness: TIntOrRatio,
) : TColor

