package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("color.hsv")
@Serializable
data class THsv(
    @SerialName("hue") val hue : TAngle, 
    @SerialName("saturation") val saturation : TIntOrRatio, 
    @SerialName("value") val value : TIntOrRatio, 
    @SerialName("alpha") val alpha : TIntOrRatio? = null, 
) : TColor
{
    override fun repr() : String = RT.structRepr("color.hsv", Triple(false, null, hue), Triple(false, null, saturation), Triple(false, null, value), Triple(false, null, alpha), )
}
