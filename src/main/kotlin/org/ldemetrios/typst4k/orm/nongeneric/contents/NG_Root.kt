@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("math.root")
@Serializable
data class NGTRoot(
    @SerialName("index") val index : NGTContentOrNone, 
    @SerialName("radicand") val radicand : NGTContent, 
) : NGTContent
{
    override fun convert() = TRoot(index?.convert().cast(), radicand?.convert().cast())
}
