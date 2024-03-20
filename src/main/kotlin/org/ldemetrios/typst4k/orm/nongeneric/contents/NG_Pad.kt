@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("pad")
@Serializable
data class NGTPad(
    @SerialName("left") val left : NGTRelative? = null, 
    @SerialName("top") val top : NGTRelative? = null, 
    @SerialName("right") val right : NGTRelative? = null, 
    @SerialName("bottom") val bottom : NGTRelative? = null, 
    @SerialName("x") val x : NGTRelative? = null, 
    @SerialName("y") val y : NGTRelative? = null, 
    @SerialName("rest") val rest : NGTRelative? = null, 
    @SerialName("body") val body : NGTContent, 
) : NGTContent
{
    override fun convert() = TPad(left?.convert().cast(), top?.convert().cast(), right?.convert().cast(), bottom?.convert().cast(), x?.convert().cast(), y?.convert().cast(), rest?.convert().cast(), body?.convert().cast())
}
