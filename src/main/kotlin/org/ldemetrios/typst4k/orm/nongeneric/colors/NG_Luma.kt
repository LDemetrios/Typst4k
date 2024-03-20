@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("luma")
@Serializable
data class NGTLuma(
    @SerialName("lightness") val lightness : NGTIntOrRatio, 
    @SerialName("alpha") val alpha : NGTRatio? = null, 
) : NGTColor
{
    override fun convert() = TLuma(lightness?.convert().cast(), alpha?.convert().cast())
}
