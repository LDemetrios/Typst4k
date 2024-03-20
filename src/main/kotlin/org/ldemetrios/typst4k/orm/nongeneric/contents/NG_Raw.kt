@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("raw")
@Serializable
data class NGTRaw(
    @SerialName("text") val text : NGTStr, 
    @SerialName("block") val block : NGTBool? = null, 
    @SerialName("lang") val lang : NGTNoneOrStr? = null, 
    @SerialName("align") val align : NGTAlignment? = null, 
    @SerialName("syntaxes") val syntaxes : NGTArrayOrStr<*, >? = null, 
    @SerialName("theme") val theme : NGTNoneOrStr? = null, 
    @SerialName("tab-size") val tabSize : NGTInt? = null, 
) : NGTContent
{
    override fun convert() = TRaw(text?.convert().cast(), block?.convert().cast(), lang?.convert().cast(), align?.convert().cast(), syntaxes?.convert().cast(), theme?.convert().cast(), tabSize?.convert().cast())
}
