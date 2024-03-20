package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("grid.cell")
@Serializable
data class TCell(
    @SerialName("body") val body : TContent, 
    @SerialName("x") val x : TAutoOrInt? = null, 
    @SerialName("y") val y : TAutoOrInt? = null, 
    @SerialName("colspan") val colspan : TInt? = null, 
    @SerialName("rowspan") val rowspan : TInt? = null, 
    @SerialName("fill") val fill : TAutoOrColorOrGradientOrNoneOrPattern? = null, 
    @SerialName("align") val align : TAlignmentOrAuto? = null, 
    @SerialName("inset") val inset : TAutoOrDictionaryOrRelative<*, >? = null, 
    @SerialName("stroke") val stroke : TColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<*, >? = null, 
    @SerialName("breakable") val breakable : TAutoOrBool? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("grid.cell", null to body, "x" to x, "y" to y, "colspan" to colspan, "rowspan" to rowspan, "fill" to fill, "align" to align, "inset" to inset, "stroke" to stroke, "breakable" to breakable, )
}
