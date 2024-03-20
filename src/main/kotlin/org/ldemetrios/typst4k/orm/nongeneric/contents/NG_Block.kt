@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("block")
@Serializable
data class NGTBlock(
    @SerialName("width") val width : NGTAutoOrRelative? = null, 
    @SerialName("height") val height : NGTAutoOrRelative? = null, 
    @SerialName("breakable") val breakable : NGTBool? = null, 
    @SerialName("fill") val fill : NGTColorOrGradientOrNoneOrPattern? = null, 
    @SerialName("stroke") val stroke : NGTColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<*, >? = null, 
    @SerialName("radius") val radius : NGTDictionaryOrRelative<*, >? = null, 
    @SerialName("inset") val inset : NGTDictionaryOrRelative<*, >? = null, 
    @SerialName("outset") val outset : NGTDictionaryOrRelative<*, >? = null, 
    @SerialName("spacing") val spacing : NGTFractionOrRelative? = null, 
    @SerialName("above") val above : NGTFractionOrRelative? = null, 
    @SerialName("below") val below : NGTFractionOrRelative? = null, 
    @SerialName("clip") val clip : NGTBool? = null, 
    @SerialName("body") val body : NGTContentOrNone, 
) : NGTContent
{
    override fun convert() = TBlock(width?.convert().cast(), height?.convert().cast(), breakable?.convert().cast(), fill?.convert().cast(), stroke?.convert().cast(), radius?.convert().cast(), inset?.convert().cast(), outset?.convert().cast(), spacing?.convert().cast(), above?.convert().cast(), below?.convert().cast(), clip?.convert().cast(), body?.convert().cast())
}
