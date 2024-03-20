@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("auto")
@Serializable
data object NGTAuto : NGTValue, 
    NGTAutoOrStr, 
    NGTArrayOrAuto<Nothing>, 
    NGTAutoOrContentOrNone, 
    NGTAlignmentOrAutoOrNone, 
    NGTAutoOrInt, 
    NGTAutoOrBool, 
    NGTAutoOrBoolOrNoneOrRelative, 
    NGTAutoOrColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<Nothing>, 
    NGTAutoOrLength, 
    NGTArrayOrAutoOrDictionaryOrStr<Nothing, Nothing>, 
    NGTArrayOrAutoOrFractionOrIntOrRelative<Nothing>, 
    NGTAlignmentOrArrayOrAuto<Nothing>, 
    NGTAutoOrNone, 
    NGTAutoOrDirection, 
    NGTAutoOrFractionOrRelative, 
    NGTAutoOrDatetimeOrNone, 
    NGTAutoOrRelative, 
    NGTAngleOrAuto, 
    NGTAutoOrColorOrGradientOrNoneOrPattern, 
    NGTAlignmentOrAuto, 
    NGTAutoOrDictionaryOrRelative<Nothing>, 
    NGTAutoOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing>, 
    NGTAutoOrColorOrGradientOrPattern, 
    NGTArrayOrAutoOrDictionaryOrNoneOrStr<Nothing, Nothing>, 
    NGTAutoOrFloat
{
    override fun convert() = TAuto
}
