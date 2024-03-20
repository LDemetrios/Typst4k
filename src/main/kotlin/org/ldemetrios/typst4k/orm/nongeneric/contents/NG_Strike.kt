@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("strike")
@Serializable
data class NGTStrike(
    @SerialName("stroke") val stroke : NGTAutoOrColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<*, >? = null, 
    @SerialName("offset") val offset : NGTAutoOrLength? = null, 
    @SerialName("extent") val extent : NGTLength? = null, 
    @SerialName("background") val background : NGTBool? = null, 
    @SerialName("body") val body : NGTContent, 
) : NGTContent
{
    override fun convert() = TStrike(stroke?.convert().cast(), offset?.convert().cast(), extent?.convert().cast(), background?.convert().cast(), body?.convert().cast())
}
