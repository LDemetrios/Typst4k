@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("color.hsl")
@Serializable
data class NGTHsl(
    @SerialName("hue") val hue : NGTAngle, 
    @SerialName("saturation") val saturation : NGTIntOrRatio, 
    @SerialName("lightness") val lightness : NGTIntOrRatio, 
    @SerialName("alpha") val alpha : NGTIntOrRatio? = null, 
) : NGTColor
{
    override fun convert() = THsl(hue?.convert().cast(), saturation?.convert().cast(), lightness?.convert().cast(), alpha?.convert().cast())
}
