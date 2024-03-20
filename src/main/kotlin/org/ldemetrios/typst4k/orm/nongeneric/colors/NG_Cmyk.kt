@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("cmyk")
@Serializable
data class NGTCmyk(
    @SerialName("cyan") val cyan : NGTRatio, 
    @SerialName("magenta") val magenta : NGTRatio, 
    @SerialName("yellow") val yellow : NGTRatio, 
    @SerialName("key") val key : NGTRatio, 
) : NGTColor
{
    override fun convert() = TCmyk(cyan?.convert().cast(), magenta?.convert().cast(), yellow?.convert().cast(), key?.convert().cast())
}
