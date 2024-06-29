package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("highlight")
@Serializable
data class THighlight(
    @SerialName("body") val body : TContent, 
    @SerialName("fill") val fill : TColorOrGradientOrPattern? = null, 
    @SerialName("stroke") val stroke : TColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<*, >? = null, 
    @SerialName("top-edge") val topEdge : TLengthOrStr? = null, 
    @SerialName("bottom-edge") val bottomEdge : TLengthOrStr? = null, 
    @SerialName("extent") val extent : TLength? = null, 
    @SerialName("radius") val radius : TDictionaryOrRelative<*, >? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("highlight", Triple(false, null, body), Triple(false, "fill", fill), Triple(false, "stroke", stroke), Triple(false, "top-edge", topEdge), Triple(false, "bottom-edge", bottomEdge), Triple(false, "extent", extent), Triple(false, "radius", radius), )
}
