package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("radial")
@Serializable
data class TRadial(
    @SerialName("stops") val stops : TArray<TArrayOrColor<TColorOrRatio, >, >, 
    @SerialName("space") val space : TStr? = null, 
    @SerialName("relative") val relative : TAutoOrStr? = null, 
    @SerialName("center") val center : TArray<TRatio, >? = null, 
    @SerialName("radius") val radius : TRatio? = null, 
    @SerialName("focal-center") val focalCenter : TArrayOrAuto<TRatio, >? = null, 
    @SerialName("focal-radius") val focalRadius : TRatio? = null, 
) : TGradient
{
    override fun repr() : String = RT.structRepr("radial", null to stops, "space" to space, "relative" to relative, "center" to center, "radius" to radius, "focal-center" to focalCenter, "focal-radius" to focalRadius, )
}
