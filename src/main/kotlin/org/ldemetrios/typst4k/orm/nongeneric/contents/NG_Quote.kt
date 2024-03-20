@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("quote")
@Serializable
data class NGTQuote(
    @SerialName("block") val block : NGTBool? = null, 
    @SerialName("quotes") val quotes : NGTAutoOrBool? = null, 
    @SerialName("attribution") val attribution : NGTContentOrLabelOrNone? = null, 
    @SerialName("body") val body : NGTContent, 
) : NGTContent
{
    override fun convert() = TQuote(block?.convert().cast(), quotes?.convert().cast(), attribution?.convert().cast(), body?.convert().cast())
}
