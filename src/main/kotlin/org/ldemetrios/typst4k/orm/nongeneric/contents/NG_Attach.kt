@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("math.attach")
@Serializable
data class NGTAttach(
    @SerialName("base") val base : NGTContent, 
    @SerialName("t") val t : NGTContentOrNone? = null, 
    @SerialName("b") val b : NGTContentOrNone? = null, 
    @SerialName("tl") val tl : NGTContentOrNone? = null, 
    @SerialName("bl") val bl : NGTContentOrNone? = null, 
    @SerialName("tr") val tr : NGTContentOrNone? = null, 
    @SerialName("br") val br : NGTContentOrNone? = null, 
) : NGTContent
{
    override fun convert() = TAttach(base?.convert().cast(), t?.convert().cast(), b?.convert().cast(), tl?.convert().cast(), bl?.convert().cast(), tr?.convert().cast(), br?.convert().cast())
}
