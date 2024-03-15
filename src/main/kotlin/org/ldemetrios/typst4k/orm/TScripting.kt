package org.ldemetrios.typst4k.orm

import org.ldemetrios.utilities.replaceAll

sealed interface TypstValue {
    fun toTypstRepr(): String
}

sealed interface TIntOrRatio : TypstValue
sealed interface TFloatOrRatio : TypstValue
sealed interface TRelativeOrFraction : TypstValue, TAutoOrRelativeOrFraction
sealed interface TStrOrNone : TypstValue
sealed interface TStrOrArray<out E : TypstValue> : TypstValue
sealed interface TAutoOrBool : TypstValue
sealed interface TContentOrArray<out E : TypstValue> : TypstValue
sealed interface TAutoOrArray<out E : TypstValue> : TypstValue
sealed interface TAutoOrRelativeOrFraction : TypstValue
sealed interface TIntOrNone : TypstValue
sealed interface TIntOrStr : TypstValue
sealed interface TColorOrGradient : TColorOrGradientOrPattern
sealed interface TColorOrGradientOrPattern : TypstValue
sealed interface TNoneOrAuto : TypstValue, TNoneOrAutoOrContent
sealed interface TLengthOrStr : TypstValue
sealed interface TAutoOrStr : TypstValue
sealed interface TAutoOrDirection : TypstValue
sealed interface TArrayOrDictionary<out E : TypstValue, out V : TypstValue> : TypstValue
sealed interface TNoneOrAutoOrContent : TypstValue
sealed interface TNoneOrContent : TNoneOrAutoOrContent

object TNone : TypstValue, TStrOrNone, TIntOrNone, TNoneOrAuto, TNoneOrAutoOrContent , TNoneOrContent{
    override fun toTypstRepr(): String = "none"
}

object TAuto : TypstValue, TAutoOrBool, TAutoOrRelativeOrFraction, TNoneOrAuto, TAutoOrStr, TAutoOrDirection,
    TAutoOrArray<Nothing> , TNoneOrAutoOrContent{
    override fun toTypstRepr(): String = "auto"
}

data class TAngle(val value: Double) : TypstValue {
    override fun toTypstRepr(): String = "${value}deg"
}

data class TArray<out E : TypstValue>(val list: List<E>) : TypstValue, TStrOrArray<E>, TContentOrArray<E>,
    TArrayOrDictionary<E, Nothing>, TAutoOrArray<E> {
    override fun toTypstRepr(): String = "(" + list.joinToString(", ") { it.toTypstRepr() } + ")"
}

data class TBool(val boolean: Boolean) : TypstValue, TAutoOrBool {
    override fun toString(): String = boolean.toString()
    override fun toTypstRepr(): String = boolean.toString()
}

sealed interface TContent : TypstValue, TContentOrArray<Nothing>, TNoneOrAutoOrContent, TNoneOrContent

internal fun scriptingRepr(name: String, values: List<Pair<String?, TypstValue?>>) =
    name + "(" + values.mapNotNull {
        if (it.first == null) {
            it.second?.toTypstRepr()
        } else if (it.second == null) {
            null
        } else {
            it.first + ": " + it.second!!.toTypstRepr()
        }
    }.joinToString(", ") + ")"

data class TDatetime(
    val year: TInt,
    val month: TInt,
    val day: TInt,
    val hour: TInt? = null,
    val minute: TInt? = null,
    val second: TInt? = null,
) : TypstValue {
    override fun toTypstRepr(): String = scriptingRepr(
        "datetime", listOf(
            "year" to year,
            "month" to month,
            "day" to day,
            "hour" to hour,
            "minute" to minute,
            "second" to second,
        )
    )
}

data class TDictionary<V : TypstValue>(val map: Map<String, V>) : TypstValue, TArrayOrDictionary<Nothing, V> {
    override fun toString(): String = map.toString()

    override fun toTypstRepr(): String = "(" + map.entries.joinToString(", ") {
        "\"" + it.key.escapeTypstStr() + "\": " + it.value.toTypstRepr()
    } + ")"
}

private fun String.escapeTypstStr() = replaceAll(
    mapOf(
        "\\" to "\\\\",
        "\r" to "\\r",
        "\t" to "\\t",
        "\n" to "\\n",
        "\"" to "\\\""
    )
)

data class TDuration(
    val seconds: TInt?, val minutes: TInt?, val hours: TInt?, val days: TInt?, val weeks: TInt?,
) : TypstValue {
    override fun toTypstRepr(): String = scriptingRepr(
        "duration", listOf(
            "seconds" to seconds,
            "minutes" to minutes,
            "hours" to hours,
            "days" to days,
            "weeks" to weeks,
        )
    )
}

data class TFloat(val value: Double) : TypstValue, TFloatOrRatio {
    override fun toString(): String = value.toString()
    override fun toTypstRepr(): String = value.toString()
}

operator fun TFloat?.plus(other: TFloat?) = TFloat((this?.value ?: .0) + (other?.value ?: .0))
operator fun TFloat?.minus(other: TFloat?) = TFloat((this?.value ?: .0) - (other?.value ?: .0))
operator fun TFloat?.unaryMinus() = TFloat(-(this?.value ?: .0))
operator fun TFloat?.times(other: TFloat?) = TFloat((this?.value ?: .0) * (other?.value ?: .0))
operator fun TFloat?.div(other: TFloat) = TFloat((this?.value ?: .0) / other.value)

data class TFraction(val value: Double) : TypstValue, TRelativeOrFraction {
    override fun toString(): String = "${value}fr"
    override fun toTypstRepr(): String = "${value}fr"
}

data class TInt(val value: Long) : TypstValue, TIntOrRatio, TIntOrNone, TIntOrStr {
    override fun toString(): String = value.toString()
    override fun toTypstRepr(): String = value.toString()
}

data class TLabel(val value: TStr) : TypstValue {
    override fun toString(): String = "<${value.value}>"
    override fun toTypstRepr(): String = "label(\"" + value.value.escapeTypstStr() + "\")"
}

data class TRegex(val value: TStr) : TypstValue {
    override fun toTypstRepr(): String = "regex(\"" + value.value.escapeTypstStr() + "\")"
}

data class TStr(val value: String) : TypstValue, TStrOrNone, TStrOrArray<Nothing>, TIntOrStr, TLengthOrStr, TAutoOrStr {
    override fun toTypstRepr(): String = "\"" + value.escapeTypstStr() + "\""
}

data class TVersion(val numbers: List<TInt>) : TypstValue {
    override fun toString(): String = "version(${numbers.joinToString(", ")})"
    override fun toTypstRepr(): String = "version(" + numbers.joinToString(", ") { it.toTypstRepr() } + ")"
}

enum class TType : TypstValue {
    array, bool, bytes, content, datetime, dictionary, duration, float, integer, label, regex, str, version, type, none, auto, length, ratio, relative, alignment, color, angle, direction, pattern, gradient;

    override fun toTypstRepr(): String = name
}

open class TRelative(private val _length: Pair<TFloat?, TFloat?>?, private val _ratio: TFloat?) : TypstValue,
    TRelativeOrFraction {
    val length get() = _length?.run { TLength(first, second) }
    val ratio get() = TRatio(_ratio ?: TFloat(.0))

    operator fun plus(other: TRelative): TRelative {
        return TRelative(
            _length?.first + other._length?.first to _length?.second + other._length?.second,
            _ratio + other._ratio
        )
    }

    operator fun minus(other: TRelative): TRelative {
        return TRelative(
            _length?.first - other._length?.first to _length?.second - other._length?.second,
            _ratio - other._ratio
        )
    }

    operator fun unaryMinus(): TRelative {
        return TRelative(
            -_length?.first to -_length?.second,
            -_ratio
        )
    }

    override fun toTypstRepr(): String {
        return listOfNotNull(
            _length?.first?.let { "${it}pt" },
            _length?.second?.let { "${it}em" },
            _ratio?.let { "${it}fr" }
        )
            .joinToString(" + ")
    }
}

data class TLength(val pts: TFloat?, val ems: TFloat?) : TRelative(pts to ems, null), TLengthOrStr

data class TRatio(val data: TFloat) : TRelative(null, data), TIntOrRatio, TFloatOrRatio

private enum class THorisontalAlignment {
    start, left, center, right, end
}

private enum class TVerticalAlignment {
    top, horizon, bottom
}

data class TAlignment private constructor(
    private val _x: THorisontalAlignment?,
    private val _y: TVerticalAlignment?
) : TypstValue {
    companion object {
        val start = TAlignment(THorisontalAlignment.start, null)
        val left = TAlignment(THorisontalAlignment.left, null)
        val right = TAlignment(THorisontalAlignment.right, null)
        val center = TAlignment(THorisontalAlignment.center, null)
        val end = TAlignment(THorisontalAlignment.end, null)
        val top = TAlignment(null, TVerticalAlignment.top)
        val horizon = TAlignment(null, TVerticalAlignment.horizon)
        val bottom = TAlignment(null, TVerticalAlignment.bottom)

        fun valueOf(value: String): TAlignment {
            return when (value) {
                "start" -> start
                "left" -> left
                "right" -> right
                "center" -> center
                "end" -> end
                "top" -> top
                "horizon" -> horizon
                "bottom" -> bottom
                else -> throw IllegalArgumentException("No object org.ldemetrios.typst4k.TAlignment.$value")
            }
        }
    }

    val x get() = if (_x == null) TNone else TAlignment(_x, null)
    val y get() = if (_y == null) TNone else TAlignment(null, _y)

    operator fun plus(other: TAlignment): TAlignment {
        if (_x != null && other._x != null) throw IllegalArgumentException("Cannot add two horizontal alignments")
        if (_y != null && other._y != null) throw IllegalArgumentException("Cannot add two vertical alignments")
        return TAlignment(_x ?: other._x, _y ?: other._y)
    }

    override fun toTypstRepr(): String {
        return listOfNotNull(
            _x?.name,
            _y?.name,
        )
            .joinToString(" + ")
    }
}

sealed interface TColor : TypstValue, TColorOrGradientOrPattern, TColorOrGradient

enum class TDirection : TAutoOrDirection, TypstValue {
    ltr,
    rtl,
    ttb,
    btt;
    override fun toTypstRepr(): String = name
}

data class TPattern(
    val size: TAutoOrArray<TAutoOrRelativeOrFraction>? = null,
    val spacing: TArray<TAutoOrRelativeOrFraction>? = null,
    val relative: TAutoOrStr? = null,
    val body: TContent
) : TypstValue, TColorOrGradientOrPattern {
    override fun toTypstRepr(): String = scriptingRepr(
        "pattern", listOf(
            "size" to size,
            "spacing" to spacing,
            "relative" to relative,
            null to body,
        )
    )
}

sealed interface TGradient : TypstValue, TColorOrGradient, TColorOrGradientOrPattern

//TODO: Rewrite binding with those types

fun type(x: TypstValue): TType = when (x) {
    is TArray<*> -> TType.array
    is TBool -> TType.bool
    is TContent -> TType.content
    is TDatetime -> TType.datetime
    is TDictionary<*> -> TType.dictionary
    is TDuration -> TType.duration
    is TFloat -> TType.float
    is TFraction -> TType.ratio
    is TInt -> TType.integer
    is TLabel -> TType.label
    is TRegex -> TType.regex
    is TStr -> TType.str
    is TVersion -> TType.version
    is TDirection -> TType.direction
    is TAlignment -> TType.alignment
    is TColor -> TType.color
    is TPattern -> TType.pattern
    is TGradient -> TType.gradient
    TNone -> TType.none
    TAuto -> TType.auto
    is TLength -> TType.length
    is TRatio -> TType.ratio
    is TRelative -> TType.relative
    is TAngle -> TType.angle
    is TType -> TType.type
}

