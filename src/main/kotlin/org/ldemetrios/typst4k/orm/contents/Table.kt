package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("table")
@Serializable
data class TTable(
    @SerialName("children") val children : TArray<TContent, >, 
    @SerialName("columns") val columns : TArrayOrAutoOrFractionOrIntOrRelative<*, >? = null, 
    @SerialName("rows") val rows : TArrayOrAutoOrFractionOrIntOrRelative<*, >? = null, 
    @SerialName("gutter") val gutter : TArrayOrAutoOrFractionOrIntOrRelative<*, >? = null, 
    @SerialName("column-gutter") val columnGutter : TArrayOrAutoOrFractionOrIntOrRelative<*, >? = null, 
    @SerialName("row-gutter") val rowGutter : TArrayOrAutoOrFractionOrIntOrRelative<*, >? = null, 
    @SerialName("fill") val fill : TArrayOrColorOrGradientOrNoneOrPattern<*, >? = null, 
    @SerialName("align") val align : TAlignmentOrArrayOrAuto<*, >? = null, 
    @SerialName("stroke") val stroke : TArrayOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<*, *, >? = null, 
    @SerialName("inset") val inset : TArrayOrDictionaryOrRelative<TFractionOrRelative, TFractionOrRelative, >? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("table", Triple(false, null, children), Triple(false, "columns", columns), Triple(false, "rows", rows), Triple(false, "gutter", gutter), Triple(false, "column-gutter", columnGutter), Triple(false, "row-gutter", rowGutter), Triple(false, "fill", fill), Triple(false, "align", align), Triple(false, "stroke", stroke), Triple(false, "inset", inset), )
}
