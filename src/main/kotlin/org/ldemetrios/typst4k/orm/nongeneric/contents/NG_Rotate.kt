@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("rotate")
@Serializable
data class NGTRotate(
    @SerialName("pos") val pos : NGTAngle? = null, 
    @SerialName("origin") val origin : NGTAlignment? = null, 
    @SerialName("reflow") val reflow : NGTBool? = null, 
    @SerialName("body") val body : NGTContent, 
) : NGTContent
{
    override fun convert() = TRotate(pos?.convert().cast(), origin?.convert().cast(), reflow?.convert().cast(), body?.convert().cast())
}
