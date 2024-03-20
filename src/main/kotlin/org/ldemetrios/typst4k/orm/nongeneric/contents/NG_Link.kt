@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("link")
@Serializable
data class NGTLink(
    @SerialName("dest") val dest : NGTDictionaryOrLabelOrLocationOrStr<TIntOrLength, >, 
    @SerialName("body") val body : NGTContent, 
) : NGTContent
{
    override fun convert() = TLink(dest?.convert().cast(), body?.convert().cast())
}
