@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("table")
@Serializable
data class NGTTable(
    @SerialName("columns") val columns : NGTArrayOrAutoOrFractionOrIntOrRelative<*, >? = null, 
    @SerialName("rows") val rows : NGTArrayOrAutoOrFractionOrIntOrRelative<*, >? = null, 
    @SerialName("gutter") val gutter : NGTArrayOrAutoOrFractionOrIntOrRelative<*, >? = null, 
    @SerialName("column-gutter") val columnGutter : NGTArrayOrAutoOrFractionOrIntOrRelative<*, >? = null, 
    @SerialName("row-gutter") val rowGutter : NGTArrayOrAutoOrFractionOrIntOrRelative<*, >? = null, 
    @SerialName("fill") val fill : NGTArrayOrColorOrGradientOrNoneOrPattern<*, >? = null, 
    @SerialName("align") val align : NGTAlignmentOrArrayOrAuto<*, >? = null, 
    @SerialName("stroke") val stroke : NGTArrayOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<*, *, >? = null, 
    @SerialName("inset") val inset : NGTArrayOrDictionaryOrRelative<TFractionOrRelative, TFractionOrRelative, >? = null, 
    @SerialName("children") val children : NGTArray<TContent, >, 
) : NGTContent
{
    override fun convert() = TTable(columns?.convert().cast(), rows?.convert().cast(), gutter?.convert().cast(), columnGutter?.convert().cast(), rowGutter?.convert().cast(), fill?.convert().cast(), align?.convert().cast(), stroke?.convert().cast(), inset?.convert().cast(), children?.convert().cast())
}
