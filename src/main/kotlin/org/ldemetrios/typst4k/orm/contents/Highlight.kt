package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("highlight")
@Serializable
data class THighlight(
    @SerialName("fill") val fill : TColorOrGradientOrPattern? = null, 
    @SerialName("stroke") val stroke : TColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<*, >? = null, 
    @SerialName("top-edge") val topEdge : TLengthOrStr? = null, 
    @SerialName("bottom-edge") val bottomEdge : TLengthOrStr? = null, 
    @SerialName("extent") val extent : TLength? = null, 
    @SerialName("radius") val radius : TDictionaryOrRelative<*, >? = null, 
    @SerialName("body") val body : TContent, 
) : TContent
{
    override fun repr() : String = RT.structRepr("highlight", "fill" to fill, "stroke" to stroke, "top-edge" to topEdge, "bottom-edge" to bottomEdge, "extent" to extent, "radius" to radius, null to body, )
}
