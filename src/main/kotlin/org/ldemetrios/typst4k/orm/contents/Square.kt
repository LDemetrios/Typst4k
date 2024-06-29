package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("square")
@Serializable
data class TSquare(
    @SerialName("body") val body : TContentOrNone? = null, 
    @SerialName("size") val size : TAutoOrLength? = null, 
    @SerialName("width") val width : TAutoOrRelative? = null, 
    @SerialName("height") val height : TAutoOrRelative? = null, 
    @SerialName("fill") val fill : TColorOrGradientOrNoneOrPattern? = null, 
    @SerialName("stroke") val stroke : TAutoOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<*, >? = null, 
    @SerialName("radius") val radius : TDictionaryOrRelative<*, >? = null, 
    @SerialName("inset") val inset : TDictionaryOrRelative<*, >? = null, 
    @SerialName("outset") val outset : TDictionaryOrRelative<*, >? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("square", Triple(false, null, body), Triple(false, "size", size), Triple(false, "width", width), Triple(false, "height", height), Triple(false, "fill", fill), Triple(false, "stroke", stroke), Triple(false, "radius", radius), Triple(false, "inset", inset), Triple(false, "outset", outset), )
}
