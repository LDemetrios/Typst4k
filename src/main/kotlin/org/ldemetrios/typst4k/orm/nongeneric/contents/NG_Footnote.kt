@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("footnote")
@Serializable
data class NGTFootnote(
    @SerialName("numbering") val numbering : NGTStr? = null, 
    @SerialName("body") val body : NGTContentOrLabel, 
) : NGTContent
{
    override fun convert() = TFootnote(numbering?.convert().cast(), body?.convert().cast())
}
