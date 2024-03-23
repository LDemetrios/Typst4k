package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("conic")
@Serializable
data class TConic(
    @SerialName("stops") val stops : TArray<TArrayOrColor<TColorOrRatio, >, >, 
    @SerialName("angle") val angle : TAngle? = null, 
    @SerialName("relative") val relative : TAutoOrStr? = null, 
    @SerialName("center") val center : TArray<TRatio, >? = null, 
) : TGradient
{
    override fun repr() : String = RT.structRepr("gradient.conic", Triple(true, null, stops), Triple(false, "angle", angle), Triple(false, "relative", relative), Triple(false, "center", center), )
}
