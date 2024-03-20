@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("highlight")
@Serializable
data class NGTHighlight(
    @SerialName("fill") val fill : NGTColorOrGradientOrPattern? = null, 
    @SerialName("stroke") val stroke : NGTColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<*, >? = null, 
    @SerialName("top-edge") val topEdge : NGTLengthOrStr? = null, 
    @SerialName("bottom-edge") val bottomEdge : NGTLengthOrStr? = null, 
    @SerialName("extent") val extent : NGTLength? = null, 
    @SerialName("radius") val radius : NGTDictionaryOrRelative<*, >? = null, 
    @SerialName("body") val body : NGTContent, 
) : NGTContent
{
    override fun convert() = THighlight(fill?.convert().cast(), stroke?.convert().cast(), topEdge?.convert().cast(), bottomEdge?.convert().cast(), extent?.convert().cast(), radius?.convert().cast(), body?.convert().cast())
}
