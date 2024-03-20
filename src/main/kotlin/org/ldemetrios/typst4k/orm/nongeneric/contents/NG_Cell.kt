@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("grid.cell")
@Serializable
data class NGTCell(
    @SerialName("body") val body : NGTContent, 
    @SerialName("x") val x : NGTAutoOrInt? = null, 
    @SerialName("y") val y : NGTAutoOrInt? = null, 
    @SerialName("colspan") val colspan : NGTInt? = null, 
    @SerialName("rowspan") val rowspan : NGTInt? = null, 
    @SerialName("fill") val fill : NGTAutoOrColorOrGradientOrNoneOrPattern? = null, 
    @SerialName("align") val align : NGTAlignmentOrAuto? = null, 
    @SerialName("inset") val inset : NGTAutoOrDictionaryOrRelative<*, >? = null, 
    @SerialName("stroke") val stroke : NGTColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<*, >? = null, 
    @SerialName("breakable") val breakable : NGTAutoOrBool? = null, 
) : NGTContent
{
    override fun convert() = TCell(body?.convert().cast(), x?.convert().cast(), y?.convert().cast(), colspan?.convert().cast(), rowspan?.convert().cast(), fill?.convert().cast(), align?.convert().cast(), inset?.convert().cast(), stroke?.convert().cast(), breakable?.convert().cast())
}
