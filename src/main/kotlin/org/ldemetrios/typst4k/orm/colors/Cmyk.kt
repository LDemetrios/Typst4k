package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("cmyk")
@Serializable
data class TCmyk(
    @SerialName("cyan") val cyan : TRatio, 
    @SerialName("magenta") val magenta : TRatio, 
    @SerialName("yellow") val yellow : TRatio, 
    @SerialName("key") val key : TRatio, 
) : TColor
{
    override fun repr() : String = RT.structRepr("cmyk", Triple(false, null, cyan), Triple(false, null, magenta), Triple(false, null, yellow), Triple(false, null, key), )
}
