@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("line")
@Serializable
data class NGTLine(
    @SerialName("start") val start : NGTArray<*, >? = null, 
    @SerialName("end") val end : NGTArrayOrNone<*, >? = null, 
    @SerialName("length") val length : NGTRelative? = null, 
    @SerialName("angle") val angle : NGTAngle? = null, 
    @SerialName("stroke") val stroke : NGTColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<*, >? = null, 
) : NGTContent
{
    override fun convert() = TLine(start?.convert().cast(), end?.convert().cast(), length?.convert().cast(), angle?.convert().cast(), stroke?.convert().cast())
}
