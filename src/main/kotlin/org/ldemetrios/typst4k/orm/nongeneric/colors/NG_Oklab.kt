@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("oklab")
@Serializable
data class NGTOklab(
    @SerialName("lightness") val lightness : NGTRatio, 
    @SerialName("a") val a : NGTFloatOrRatio, 
    @SerialName("b") val b : NGTFloatOrRatio, 
    @SerialName("alpha") val alpha : NGTRatio? = null, 
) : NGTColor
{
    override fun convert() = TOklab(lightness?.convert().cast(), a?.convert().cast(), b?.convert().cast(), alpha?.convert().cast())
}
