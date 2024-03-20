@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("color.hsv")
@Serializable
data class NGTHsv(
    @SerialName("hue") val hue : NGTAngle, 
    @SerialName("saturation") val saturation : NGTIntOrRatio, 
    @SerialName("value") val value : NGTIntOrRatio, 
    @SerialName("alpha") val alpha : NGTIntOrRatio? = null, 
) : NGTColor
{
    override fun convert() = THsv(hue?.convert().cast(), saturation?.convert().cast(), value?.convert().cast(), alpha?.convert().cast())
}
