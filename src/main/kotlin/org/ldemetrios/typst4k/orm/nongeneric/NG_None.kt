@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("none")
@Serializable
data object NGTNone : NGTValue, 
    NGTAutoOrContentOrNone, 
    NGTContentOrNone, 
    NGTNoneOrStr, 
    NGTAlignmentOrAutoOrNone, 
    NGTColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing>, 
    NGTIntOrNone, 
    NGTAutoOrBoolOrNoneOrRelative, 
    NGTContentOrLabelOrNone, 
    NGTArrayOrColorOrGradientOrNoneOrPattern<Nothing>, 
    NGTArrayOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing, Nothing>, 
    NGTAutoOrNone, 
    NGTAutoOrDatetimeOrNone, 
    NGTDictionaryOrIntOrNone<Nothing>, 
    NGTNoneOrNonecontent, 
    NGTColorOrGradientOrNoneOrPattern, 
    NGTAutoOrColorOrGradientOrNoneOrPattern, 
    NGTFractionOrNoneOrRelative, 
    NGTAutoOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing>, 
    NGTArrayOrNone<Nothing>, 
    NGTArrayOrAutoOrDictionaryOrNoneOrStr<Nothing, Nothing>
{
    override fun convert() = TNone
}
