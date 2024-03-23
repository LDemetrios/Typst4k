package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("rect")
@Serializable
data class TRect(
    @SerialName("width") val width : TAutoOrRelative? = null, 
    @SerialName("height") val height : TAutoOrRelative? = null, 
    @SerialName("fill") val fill : TColorOrGradientOrNoneOrPattern? = null, 
    @SerialName("stroke") val stroke : TAutoOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<*, >? = null, 
    @SerialName("radius") val radius : TDictionaryOrRelative<*, >? = null, 
    @SerialName("inset") val inset : TDictionaryOrRelative<*, >? = null, 
    @SerialName("outset") val outset : TDictionaryOrRelative<*, >? = null, 
    @SerialName("body") val body : TContentOrNone? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("rect", Triple(false, "width", width), Triple(false, "height", height), Triple(false, "fill", fill), Triple(false, "stroke", stroke), Triple(false, "radius", radius), Triple(false, "inset", inset), Triple(false, "outset", outset), Triple(false, null, body), )
}
