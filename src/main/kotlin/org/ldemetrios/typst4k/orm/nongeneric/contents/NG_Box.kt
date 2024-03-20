@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("box")
@Serializable
data class NGTBox(
    @SerialName("width") val width : NGTAutoOrFractionOrRelative? = null, 
    @SerialName("height") val height : NGTAutoOrRelative? = null, 
    @SerialName("baseline") val baseline : NGTRelative? = null, 
    @SerialName("fill") val fill : NGTColorOrGradientOrNoneOrPattern? = null, 
    @SerialName("stroke") val stroke : NGTColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<*, >? = null, 
    @SerialName("radius") val radius : NGTDictionaryOrRelative<*, >? = null, 
    @SerialName("inset") val inset : NGTDictionaryOrRelative<*, >? = null, 
    @SerialName("outset") val outset : NGTDictionaryOrRelative<*, >? = null, 
    @SerialName("clip") val clip : NGTBool? = null, 
    @SerialName("body") val body : NGTContentOrNone, 
) : NGTContent
{
    override fun convert() = TBox(width?.convert().cast(), height?.convert().cast(), baseline?.convert().cast(), fill?.convert().cast(), stroke?.convert().cast(), radius?.convert().cast(), inset?.convert().cast(), outset?.convert().cast(), clip?.convert().cast(), body?.convert().cast())
}
