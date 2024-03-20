package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("strike")
@Serializable
data class TStrike(
    @SerialName("stroke") val stroke : TAutoOrColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<*, >? = null, 
    @SerialName("offset") val offset : TAutoOrLength? = null, 
    @SerialName("extent") val extent : TLength? = null, 
    @SerialName("background") val background : TBool? = null, 
    @SerialName("body") val body : TContent, 
) : TContent
{
    override fun repr() : String = RT.structRepr("strike", "stroke" to stroke, "offset" to offset, "extent" to extent, "background" to background, null to body, )
}
