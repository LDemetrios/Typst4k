@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("align")
@Serializable
data class NGTAlign(
    @SerialName("alignment") val alignment : NGTAlignment, 
    @SerialName("body") val body : NGTContent, 
) : NGTContent
{
    override fun convert() = TAlign(alignment?.convert().cast(), body?.convert().cast())
}
