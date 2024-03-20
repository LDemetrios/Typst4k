package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("math.accent")
@Serializable
data class TAccent(
    @SerialName("base") val base : TContent, 
    @SerialName("accent") val accent : TContentOrStr, 
    @SerialName("size") val size : TAutoOrRelative? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("math.accent", null to base, null to accent, "size" to size, )
}
