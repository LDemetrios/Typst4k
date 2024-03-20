@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("math.accent")
@Serializable
data class NGTAccent(
    @SerialName("base") val base : NGTContent, 
    @SerialName("accent") val accent : NGTContentOrStr, 
    @SerialName("size") val size : NGTAutoOrRelative? = null, 
) : NGTContent
{
    override fun convert() = TAccent(base?.convert().cast(), accent?.convert().cast(), size?.convert().cast())
}
