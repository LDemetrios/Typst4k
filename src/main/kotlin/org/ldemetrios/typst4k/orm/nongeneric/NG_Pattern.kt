@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("pattern")
@Serializable
data class NGTPattern(
    @SerialName("size") val size : NGTArrayOrAuto<*, >? = null, 
    @SerialName("spacing") val spacing : NGTArray<TLength, >? = null, 
    @SerialName("relative") val relative : NGTAutoOrStr? = null, 
) : NGTValue, 
    NGTColorOrGradientOrPattern, 
    NGTColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing>, 
    NGTAutoOrColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<Nothing>, 
    NGTArrayOrColorOrGradientOrNoneOrPattern<Nothing>, 
    NGTArrayOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing, Nothing>, 
    NGTColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<Nothing>, 
    NGTColorOrGradientOrNoneOrPattern, 
    NGTAutoOrColorOrGradientOrNoneOrPattern, 
    NGTAutoOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing>, 
    NGTAutoOrColorOrGradientOrPattern
{
    override fun convert() = TPattern(size?.convert().cast(), spacing?.convert().cast(), relative?.convert().cast())
}
