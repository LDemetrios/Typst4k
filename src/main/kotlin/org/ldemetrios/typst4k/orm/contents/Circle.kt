package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("circle")
@Serializable
data class TCircle(
    @SerialName("radius") val radius : TLength? = null, 
    @SerialName("width") val width : TAutoOrRelative? = null, 
    @SerialName("height") val height : TAutoOrRelative? = null, 
    @SerialName("fill") val fill : TColorOrGradientOrNoneOrPattern? = null, 
    @SerialName("stroke") val stroke : TAutoOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<*, >? = null, 
    @SerialName("inset") val inset : TDictionaryOrRelative<*, >? = null, 
    @SerialName("outset") val outset : TDictionaryOrRelative<*, >? = null, 
    @SerialName("body") val body : TContentOrNone? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("circle", "radius" to radius, "width" to width, "height" to height, "fill" to fill, "stroke" to stroke, "inset" to inset, "outset" to outset, null to body, )
}
