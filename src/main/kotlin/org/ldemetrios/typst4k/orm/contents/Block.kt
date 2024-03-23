package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("block")
@Serializable
data class TBlock(
    @SerialName("width") val width : TAutoOrRelative? = null, 
    @SerialName("height") val height : TAutoOrRelative? = null, 
    @SerialName("breakable") val breakable : TBool? = null, 
    @SerialName("fill") val fill : TColorOrGradientOrNoneOrPattern? = null, 
    @SerialName("stroke") val stroke : TColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<*, >? = null, 
    @SerialName("radius") val radius : TDictionaryOrRelative<*, >? = null, 
    @SerialName("inset") val inset : TDictionaryOrRelative<*, >? = null, 
    @SerialName("outset") val outset : TDictionaryOrRelative<*, >? = null, 
    @SerialName("spacing") val spacing : TFractionOrRelative? = null, 
    @SerialName("above") val above : TFractionOrRelative? = null, 
    @SerialName("below") val below : TFractionOrRelative? = null, 
    @SerialName("clip") val clip : TBool? = null, 
    @SerialName("body") val body : TContentOrNone, 
) : TContent
{
    override fun repr() : String = RT.structRepr("block", Triple(false, "width", width), Triple(false, "height", height), Triple(false, "breakable", breakable), Triple(false, "fill", fill), Triple(false, "stroke", stroke), Triple(false, "radius", radius), Triple(false, "inset", inset), Triple(false, "outset", outset), Triple(false, "spacing", spacing), Triple(false, "above", above), Triple(false, "below", below), Triple(false, "clip", clip), Triple(false, null, body), )
}
