@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("grid.vline")
@Serializable
data class NGTVline(
    @SerialName("x") val x : NGTAutoOrInt? = null, 
    @SerialName("start") val start : NGTInt? = null, 
    @SerialName("end") val end : NGTIntOrNone? = null, 
    @SerialName("stroke") val stroke : NGTColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<*, >? = null, 
    @SerialName("position") val position : NGTAlignment? = null, 
) : NGTContent
{
    override fun convert() = TVline(x?.convert().cast(), start?.convert().cast(), end?.convert().cast(), stroke?.convert().cast(), position?.convert().cast())
}
