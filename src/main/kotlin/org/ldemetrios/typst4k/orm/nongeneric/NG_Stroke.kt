@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("stroke")
@Serializable
data class NGTStroke(
    @SerialName("paint") val paint : NGTAutoOrColorOrGradientOrPattern? = null, 
    @SerialName("thickness") val thickness : NGTAutoOrLength? = null, 
    @SerialName("cap") val cap : NGTAutoOrStr? = null, 
    @SerialName("join") val join : NGTAutoOrStr? = null, 
    @SerialName("dash") val dash : NGTArrayOrAutoOrDictionaryOrNoneOrStr<*, *, >? = null, 
    @SerialName("miter-limit") val miterLimit : NGTAutoOrFloat? = null, 
) : NGTValue, 
    NGTColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing>, 
    NGTAutoOrColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<Nothing>, 
    NGTArrayOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing, Nothing>, 
    NGTColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<Nothing>, 
    NGTAutoOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing>
{
    override fun convert() = TStroke(paint?.convert().cast(), thickness?.convert().cast(), cap?.convert().cast(), join?.convert().cast(), dash?.convert().cast(), miterLimit?.convert().cast())
}
