package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("polygon")
@Serializable
data class TPolygon(
    @SerialName("fill") val fill : TColorOrGradientOrNoneOrPattern? = null, 
    @SerialName("stroke") val stroke : TAutoOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<*, >? = null, 
    @SerialName("vertices") val vertices : TArray<TArray<TRelative, >, >, 
) : TContent
{
    override fun repr() : String = RT.structRepr("polygon", "fill" to fill, "stroke" to stroke, null to vertices, )
}
