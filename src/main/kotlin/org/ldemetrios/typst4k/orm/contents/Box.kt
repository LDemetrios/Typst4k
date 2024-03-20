package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("box")
@Serializable
data class TBox(
    @SerialName("width") val width : TAutoOrFractionOrRelative? = null, 
    @SerialName("height") val height : TAutoOrRelative? = null, 
    @SerialName("baseline") val baseline : TRelative? = null, 
    @SerialName("fill") val fill : TColorOrGradientOrNoneOrPattern? = null, 
    @SerialName("stroke") val stroke : TColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<*, >? = null, 
    @SerialName("radius") val radius : TDictionaryOrRelative<*, >? = null, 
    @SerialName("inset") val inset : TDictionaryOrRelative<*, >? = null, 
    @SerialName("outset") val outset : TDictionaryOrRelative<*, >? = null, 
    @SerialName("clip") val clip : TBool? = null, 
    @SerialName("body") val body : TContentOrNone, 
) : TContent
{
    override fun repr() : String = RT.structRepr("box", "width" to width, "height" to height, "baseline" to baseline, "fill" to fill, "stroke" to stroke, "radius" to radius, "inset" to inset, "outset" to outset, "clip" to clip, null to body, )
}
