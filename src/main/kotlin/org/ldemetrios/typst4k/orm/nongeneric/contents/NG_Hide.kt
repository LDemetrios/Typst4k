@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("hide")
@Serializable
data class NGTHide(
    @SerialName("body") val body : NGTContent, 
) : NGTContent
{
    override fun convert() = THide(body?.convert().cast())
}
