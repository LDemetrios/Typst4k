@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("grid.hline")
@Serializable
data class NGTHline(
    @SerialName("y") val y : NGTAutoOrInt? = null, 
    @SerialName("start") val start : NGTInt? = null, 
    @SerialName("end") val end : NGTIntOrNone? = null, 
    @SerialName("stroke") val stroke : NGTColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<*, >? = null, 
    @SerialName("position") val position : NGTAlignment? = null, 
) : NGTContent
{
    override fun convert() = THline(y?.convert().cast(), start?.convert().cast(), end?.convert().cast(), stroke?.convert().cast(), position?.convert().cast())
}
