package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("path")
@Serializable
data class TPath(
    @SerialName("fill") val fill : TColorOrGradientOrNoneOrPattern? = null, 
    @SerialName("stroke") val stroke : TAutoOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<*, >? = null, 
    @SerialName("closed") val closed : TBool? = null, 
    @SerialName("vertices") val vertices : TArray<TArray<*, >, >, 
) : TContent
{
    override fun repr() : String = RT.structRepr("path", "fill" to fill, "stroke" to stroke, "closed" to closed, null to vertices, )
}
