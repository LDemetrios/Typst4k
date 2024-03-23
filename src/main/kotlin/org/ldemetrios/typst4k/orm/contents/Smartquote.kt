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
    override fun repr() : String = RT.structRepr("smartquote", Triple(false, "double", double), Triple(false, "enabled", enabled), Triple(false, "alternative", alternative), Triple(false, "quotes", quotes), )
}
