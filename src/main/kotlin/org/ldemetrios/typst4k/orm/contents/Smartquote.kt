package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("smartquote")
@Serializable
data class TSmartquote(
    @SerialName("double") val double : TBool? = null, 
    @SerialName("enabled") val enabled : TBool? = null, 
    @SerialName("alternative") val alternative : TBool? = null, 
    @SerialName("quotes") val quotes : TArrayOrAutoOrDictionaryOrStr<*, *, >? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("smartquote", "double" to double, "enabled" to enabled, "alternative" to alternative, "quotes" to quotes, )
}
