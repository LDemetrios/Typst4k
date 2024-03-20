@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("conic")
@Serializable
data class NGTConic(
    @SerialName("stops") val stops : NGTArray<TArrayOrColor<TColorOrRatio, >, >, 
    @SerialName("angle") val angle : NGTAngle? = null, 
    @SerialName("space") val space : NGTStr? = null, 
    @SerialName("relative") val relative : NGTAutoOrStr? = null, 
    @SerialName("center") val center : NGTArray<TRatio, >? = null, 
) : NGTGradient
{
    override fun convert() = TConic(stops?.convert().cast(), angle?.convert().cast(), space?.convert().cast(), relative?.convert().cast(), center?.convert().cast())
}
