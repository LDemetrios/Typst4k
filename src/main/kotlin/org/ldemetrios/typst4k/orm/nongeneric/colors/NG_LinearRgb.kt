@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("color.linear-rgb")
@Serializable
data class NGTLinearRgb(
    @SerialName("red") val red : NGTIntOrRatio, 
    @SerialName("green") val green : NGTIntOrRatio, 
    @SerialName("blue") val blue : NGTIntOrRatio, 
    @SerialName("alpha") val alpha : NGTIntOrRatio? = null, 
) : NGTColor
{
    override fun convert() = TLinearRgb(red?.convert().cast(), green?.convert().cast(), blue?.convert().cast(), alpha?.convert().cast())
}
