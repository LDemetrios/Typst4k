package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("strike")
@Serializable
data class TStrike(
    @SerialName("body") val body : TContent, 
    @SerialName("stroke") val stroke : TAutoOrColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<*, >? = null, 
    @SerialName("offset") val offset : TAutoOrLength? = null, 
    @SerialName("extent") val extent : TLength? = null, 
    @SerialName("background") val background : TBool? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("strike", Triple(false, null, body), Triple(false, "stroke", stroke), Triple(false, "offset", offset), Triple(false, "extent", extent), Triple(false, "background", background), )
}
