@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("oklch")
@Serializable
data class NGTOklch(
    @SerialName("lightness") val lightness : NGTRatio, 
    @SerialName("chroma") val chroma : NGTFloatOrRatio, 
    @SerialName("hue") val hue : NGTAngle, 
    @SerialName("alpha") val alpha : NGTRatio? = null, 
) : NGTColor
{
    override fun convert() = TOklch(lightness?.convert().cast(), chroma?.convert().cast(), hue?.convert().cast(), alpha?.convert().cast())
}
