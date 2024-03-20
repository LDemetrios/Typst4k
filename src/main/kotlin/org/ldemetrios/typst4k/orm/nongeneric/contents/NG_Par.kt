@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("par")
@Serializable
data class NGTPar(
    @SerialName("leading") val leading : NGTLength? = null, 
    @SerialName("justify") val justify : NGTBool? = null, 
    @SerialName("linebreaks") val linebreaks : NGTAutoOrStr? = null, 
    @SerialName("first-line-indent") val firstLineIndent : NGTLength? = null, 
    @SerialName("hanging-indent") val hangingIndent : NGTLength? = null, 
    @SerialName("body") val body : NGTContent, 
) : NGTContent
{
    override fun convert() = TPar(leading?.convert().cast(), justify?.convert().cast(), linebreaks?.convert().cast(), firstLineIndent?.convert().cast(), hangingIndent?.convert().cast(), body?.convert().cast())
}
