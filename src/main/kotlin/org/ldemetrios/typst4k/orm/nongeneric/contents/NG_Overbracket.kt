@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("math.overbracket")
@Serializable
data class NGTOverbracket(
    @SerialName("body") val body : NGTContent, 
    @SerialName("annotation") val annotation : NGTContentOrNone? = null, 
) : NGTContent
{
    override fun convert() = TOverbracket(body?.convert().cast(), annotation?.convert().cast())
}
