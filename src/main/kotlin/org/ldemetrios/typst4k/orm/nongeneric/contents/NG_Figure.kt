@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("figure")
@Serializable
data class NGTFigure(
    @SerialName("body") val body : NGTContent, 
    @SerialName("placement") val placement : NGTAlignmentOrAutoOrNone? = null, 
    @SerialName("caption") val caption : NGTContentOrNone? = null, 
    @SerialName("kind") val kind : NGTAutoOrStr? = null, 
    @SerialName("supplement") val supplement : NGTAutoOrContentOrNone? = null, 
    @SerialName("numbering") val numbering : NGTNoneOrStr? = null, 
    @SerialName("gap") val gap : NGTLength? = null, 
    @SerialName("outlined") val outlined : NGTBool? = null, 
) : NGTContent
{
    override fun convert() = TFigure(body?.convert().cast(), placement?.convert().cast(), caption?.convert().cast(), kind?.convert().cast(), supplement?.convert().cast(), numbering?.convert().cast(), gap?.convert().cast(), outlined?.convert().cast())
}
