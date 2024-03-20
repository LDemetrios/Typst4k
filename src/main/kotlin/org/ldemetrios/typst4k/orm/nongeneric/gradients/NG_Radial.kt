@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("radial")
@Serializable
data class NGTRadial(
    @SerialName("stops") val stops : NGTArray<TArrayOrColor<TColorOrRatio, >, >, 
    @SerialName("space") val space : NGTStr? = null, 
    @SerialName("relative") val relative : NGTAutoOrStr? = null, 
    @SerialName("center") val center : NGTArray<TRatio, >? = null, 
    @SerialName("radius") val radius : NGTRatio? = null, 
    @SerialName("focal-center") val focalCenter : NGTArrayOrAuto<TRatio, >? = null, 
    @SerialName("focal-radius") val focalRadius : NGTRatio? = null, 
) : NGTGradient
{
    override fun convert() = TRadial(stops?.convert().cast(), space?.convert().cast(), relative?.convert().cast(), center?.convert().cast(), radius?.convert().cast(), focalCenter?.convert().cast(), focalRadius?.convert().cast())
}
