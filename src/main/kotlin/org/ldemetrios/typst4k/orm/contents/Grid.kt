package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("grid")
@Serializable
data class TGrid(
    @SerialName("columns") val columns : TArrayOrAutoOrFractionOrIntOrRelative<*, >? = null, 
    @SerialName("rows") val rows : TArrayOrAutoOrFractionOrIntOrRelative<*, >? = null, 
    @SerialName("gutter") val gutter : TArrayOrAutoOrFractionOrIntOrRelative<*, >? = null, 
    @SerialName("column-gutter") val columnGutter : TArrayOrAutoOrFractionOrIntOrRelative<*, >? = null, 
    @SerialName("row-gutter") val rowGutter : TArrayOrAutoOrFractionOrIntOrRelative<*, >? = null, 
    @SerialName("fill") val fill : TArrayOrColorOrGradientOrNoneOrPattern<*, >? = null, 
    @SerialName("align") val align : TAlignmentOrArrayOrAuto<*, >? = null, 
    @SerialName("stroke") val stroke : TArrayOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<*, *, >? = null, 
    @SerialName("inset") val inset : TArrayOrDictionaryOrRelative<*, *, >? = null, 
    @SerialName("children") val children : TArray<TContent, >, 
) : TContent
{
    override fun repr() : String = RT.structRepr("grid", "columns" to columns, "rows" to rows, "gutter" to gutter, "column-gutter" to columnGutter, "row-gutter" to rowGutter, "fill" to fill, "align" to align, "stroke" to stroke, "inset" to inset, null to children, )
}
