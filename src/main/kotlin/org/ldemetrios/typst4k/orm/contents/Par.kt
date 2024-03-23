package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("par")
@Serializable
data class TPar(
    @SerialName("leading") val leading : TLength? = null, 
    @SerialName("justify") val justify : TBool? = null, 
    @SerialName("linebreaks") val linebreaks : TAutoOrStr? = null, 
    @SerialName("first-line-indent") val firstLineIndent : TLength? = null, 
    @SerialName("hanging-indent") val hangingIndent : TLength? = null, 
    @SerialName("body") val body : TContent, 
) : TContent
{
    override fun repr() : String = RT.structRepr("par", Triple(false, "leading", leading), Triple(false, "justify", justify), Triple(false, "linebreaks", linebreaks), Triple(false, "first-line-indent", firstLineIndent), Triple(false, "hanging-indent", hangingIndent), Triple(false, null, body), )
}
