@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("linear")
@Serializable
data class NGTLinear(
    @SerialName("stops") val stops : NGTArray<TArrayOrColor<TColorOrRatio, >, >, 
    @SerialName("space") val space : NGTStr? = null, 
    @SerialName("relative") val relative : NGTAutoOrStr? = null, 
    @SerialName("dir") val dir : NGTDirection? = null, 
    @SerialName("angle") val angle : NGTAngle? = null, 
) : NGTGradient
{
    override fun convert() = TLinear(stops?.convert().cast(), space?.convert().cast(), relative?.convert().cast(), dir?.convert().cast(), angle?.convert().cast())
}
