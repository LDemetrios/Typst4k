@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("math.mid")
@Serializable
data class NGTMid(
    @SerialName("body") val body : NGTContent, 
) : NGTContent
{
    override fun convert() = TMid(body?.convert().cast())
}
