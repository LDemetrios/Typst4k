@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("scale")
@Serializable
data class NGTScale(
    @SerialName("x") val x : NGTRatio? = null, 
    @SerialName("y") val y : NGTRatio? = null, 
    @SerialName("origin") val origin : NGTAlignment? = null, 
    @SerialName("reflow") val reflow : NGTBool? = null, 
    @SerialName("body") val body : NGTContent, 
) : NGTContent
{
    override fun convert() = TScale(x?.convert().cast(), y?.convert().cast(), origin?.convert().cast(), reflow?.convert().cast(), body?.convert().cast())
}
