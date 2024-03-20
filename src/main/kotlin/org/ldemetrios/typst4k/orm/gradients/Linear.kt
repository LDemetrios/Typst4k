package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("linear")
@Serializable
data class TLinear(
    @SerialName("stops") val stops : TArray<TArrayOrColor<TColorOrRatio, >, >, 
    @SerialName("space") val space : TStr? = null, 
    @SerialName("relative") val relative : TAutoOrStr? = null, 
    @SerialName("dir") val dir : TDirection? = null, 
    @SerialName("angle") val angle : TAngle? = null, 
) : TGradient
{
    override fun repr() : String = RT.structRepr("linear", null to stops, "space" to space, "relative" to relative, "dir" to dir, "angle" to angle, )
}
