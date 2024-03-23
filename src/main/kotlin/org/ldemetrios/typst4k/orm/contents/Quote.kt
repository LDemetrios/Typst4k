package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("quote")
@Serializable
data class TQuote(
    @SerialName("block") val block : TBool? = null, 
    @SerialName("quotes") val quotes : TAutoOrBool? = null, 
    @SerialName("attribution") val attribution : TContentOrLabelOrNone? = null, 
    @SerialName("body") val body : TContent, 
) : TContent
{
    override fun repr() : String = RT.structRepr("quote", Triple(false, "block", block), Triple(false, "quotes", quotes), Triple(false, "attribution", attribution), Triple(false, null, body), )
}
