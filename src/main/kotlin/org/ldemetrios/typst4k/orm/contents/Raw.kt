package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("raw")
@Serializable
data class TRaw(
    @SerialName("text") val text : TStr, 
    @SerialName("block") val block : TBool? = null, 
    @SerialName("lang") val lang : TNoneOrStr? = null, 
    @SerialName("align") val align : TAlignment? = null, 
    @SerialName("syntaxes") val syntaxes : TArrayOrStr<*, >? = null, 
    @SerialName("theme") val theme : TNoneOrStr? = null, 
    @SerialName("tab-size") val tabSize : TInt? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("raw", Triple(false, null, text), Triple(false, "block", block), Triple(false, "lang", lang), Triple(false, "align", align), Triple(false, "syntaxes", syntaxes), Triple(false, "theme", theme), Triple(false, "tab-size", tabSize), )
}
