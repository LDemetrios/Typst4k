package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("color.linear-rgb")
@Serializable
data class TLinearRgb(
    @SerialName("red") val red : TIntOrRatio, 
    @SerialName("green") val green : TIntOrRatio, 
    @SerialName("blue") val blue : TIntOrRatio, 
    @SerialName("alpha") val alpha : TIntOrRatio? = null, 
) : TColor
{
    override fun repr() : String = RT.structRepr("color.linear-rgb", null to red, null to green, null to blue, null to alpha, )
}
