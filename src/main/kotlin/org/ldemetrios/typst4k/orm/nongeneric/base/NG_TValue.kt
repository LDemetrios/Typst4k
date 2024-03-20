@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@Serializable
sealed interface NGTValue {
   fun convert() : TValue
}


@Serializable
sealed interface NGTIntOrRatio : NGTValue
@Serializable
sealed interface NGTFloatOrRatio : NGTValue
@Serializable
sealed interface NGTArrayOrColor<out E : TValue> : NGTValue, 
    NGTArrayOrColorOrGradientOrNoneOrPattern<E>, 
    NGTArrayOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<E, Nothing>
@Serializable
sealed interface NGTColorOrRatio : NGTValue
@Serializable
sealed interface NGTAutoOrStr : NGTValue, 
    NGTArrayOrAutoOrDictionaryOrStr<Nothing, Nothing>, 
    NGTArrayOrAutoOrDictionaryOrNoneOrStr<Nothing, Nothing>
@Serializable
sealed interface NGTArrayOrAuto<out E : TValue> : NGTValue, 
    NGTArrayOrAutoOrDictionaryOrStr<E, Nothing>, 
    NGTArrayOrAutoOrFractionOrIntOrRelative<E>, 
    NGTAlignmentOrArrayOrAuto<E>, 
    NGTArrayOrAutoOrDictionaryOrNoneOrStr<E, Nothing>
@Serializable
sealed interface NGTArrayOrStr<out E : TValue> : NGTValue, 
    NGTArrayOrAutoOrDictionaryOrStr<E, Nothing>, 
    NGTArrayOrAutoOrDictionaryOrNoneOrStr<E, Nothing>
@Serializable
sealed interface NGTAutoOrContentOrNone : NGTValue
@Serializable
sealed interface NGTContentOrNone : NGTValue, 
    NGTAutoOrContentOrNone, 
    NGTContentOrLabelOrNone
@Serializable
sealed interface NGTNoneOrStr : NGTValue, 
    NGTArrayOrAutoOrDictionaryOrNoneOrStr<Nothing, Nothing>
@Serializable
sealed interface NGTAlignmentOrAutoOrNone : NGTValue
@Serializable
sealed interface NGTContentOrLabel : NGTValue, 
    NGTContentOrLabelOrNone
@Serializable
sealed interface NGTFractionOrRelative : NGTValue, 
    NGTArrayOrAutoOrFractionOrIntOrRelative<Nothing>, 
    NGTAutoOrFractionOrRelative, 
    NGTFractionOrNoneOrRelative, 
    NGTContentOrFractionOrRelative
@Serializable
sealed interface NGTAutoOrInt : NGTValue, 
    NGTArrayOrAutoOrFractionOrIntOrRelative<Nothing>
@Serializable
sealed interface NGTAutoOrBool : NGTValue, 
    NGTAutoOrBoolOrNoneOrRelative
@Serializable
sealed interface NGTColorOrGradientOrPattern : NGTValue, 
    NGTColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing>, 
    NGTAutoOrColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<Nothing>, 
    NGTArrayOrColorOrGradientOrNoneOrPattern<Nothing>, 
    NGTArrayOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing, Nothing>, 
    NGTColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<Nothing>, 
    NGTColorOrGradientOrNoneOrPattern, 
    NGTAutoOrColorOrGradientOrNoneOrPattern, 
    NGTAutoOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing>, 
    NGTAutoOrColorOrGradientOrPattern
@Serializable
sealed interface NGTColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<out V : TValue> : NGTValue, 
    NGTArrayOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing, V>, 
    NGTAutoOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<V>
@Serializable
sealed interface NGTLengthOrStr : NGTValue
@Serializable
sealed interface NGTDictionaryOrRelative<out V : TValue> : NGTValue, 
    NGTArrayOrDictionaryOrRelative<Nothing, V>, 
    NGTAutoOrDictionaryOrRelative<V>
@Serializable
sealed interface NGTDictionaryOrLabelOrLocationOrStr<out V : TValue> : NGTValue
@Serializable
sealed interface NGTIntOrLength : NGTValue
@Serializable
sealed interface NGTLabelOrLocationOrSelector : NGTValue
@Serializable
sealed interface NGTIntOrNone : NGTValue, 
    NGTDictionaryOrIntOrNone<Nothing>
@Serializable
sealed interface NGTAutoOrBoolOrNoneOrRelative : NGTValue
@Serializable
sealed interface NGTAutoOrColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<out V : TValue> : NGTValue, 
    NGTAutoOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<V>
@Serializable
sealed interface NGTAutoOrLength : NGTValue, 
    NGTAutoOrColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<Nothing>, 
    NGTAutoOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing>
@Serializable
sealed interface NGTContentOrLabelOrNone : NGTValue
@Serializable
sealed interface NGTArrayOrAutoOrDictionaryOrStr<out E : TValue, out V : TValue> : NGTValue, 
    NGTArrayOrAutoOrDictionaryOrNoneOrStr<E, V>
@Serializable
sealed interface NGTArrayOrAutoOrFractionOrIntOrRelative<out E : TValue> : NGTValue
@Serializable
sealed interface NGTArrayOrColorOrGradientOrNoneOrPattern<out E : TValue> : NGTValue, 
    NGTArrayOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<E, Nothing>
@Serializable
sealed interface NGTAlignmentOrArrayOrAuto<out E : TValue> : NGTValue
@Serializable
sealed interface NGTArrayOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<out E : TValue, out V : TValue> : NGTValue
@Serializable
sealed interface NGTArrayOrDictionaryOrRelative<out E : TValue, out V : TValue> : NGTValue
@Serializable
sealed interface NGTIntOrStr : NGTValue
@Serializable
sealed interface NGTAutoOrNone : NGTValue, 
    NGTAutoOrContentOrNone, 
    NGTAlignmentOrAutoOrNone, 
    NGTAutoOrBoolOrNoneOrRelative, 
    NGTAutoOrDatetimeOrNone, 
    NGTAutoOrColorOrGradientOrNoneOrPattern, 
    NGTAutoOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing>, 
    NGTArrayOrAutoOrDictionaryOrNoneOrStr<Nothing, Nothing>
@Serializable
sealed interface NGTAutoOrDirection : NGTValue
@Serializable
sealed interface NGTArrayOrDictionary<out E : TValue, out V : TValue> : NGTValue, 
    NGTArrayOrAutoOrDictionaryOrStr<E, V>, 
    NGTArrayOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<E, V>, 
    NGTArrayOrDictionaryOrRelative<E, V>, 
    NGTArrayOrAutoOrDictionaryOrNoneOrStr<E, V>
@Serializable
sealed interface NGTArrayOrContent<out E : TValue> : NGTValue
@Serializable
sealed interface NGTAutoOrFractionOrRelative : NGTValue, 
    NGTArrayOrAutoOrFractionOrIntOrRelative<Nothing>
@Serializable
sealed interface NGTAutoOrDatetimeOrNone : NGTValue
@Serializable
sealed interface NGTContentOrStr : NGTValue
@Serializable
sealed interface NGTAutoOrRelative : NGTValue, 
    NGTAutoOrBoolOrNoneOrRelative, 
    NGTArrayOrAutoOrFractionOrIntOrRelative<Nothing>, 
    NGTAutoOrFractionOrRelative, 
    NGTAutoOrDictionaryOrRelative<Nothing>
@Serializable
sealed interface NGTAngleOrAuto : NGTValue
@Serializable
sealed interface NGTColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<out V : TValue> : NGTValue, 
    NGTColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<V>, 
    NGTAutoOrColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<V>, 
    NGTArrayOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing, V>, 
    NGTAutoOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<V>
@Serializable
sealed interface NGTDictionaryOrIntOrNone<out V : TValue> : NGTValue
@Serializable
sealed interface NGTNoneOrNonecontent : NGTValue
@Serializable
sealed interface NGTColorOrGradientOrNoneOrPattern : NGTValue, 
    NGTColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing>, 
    NGTArrayOrColorOrGradientOrNoneOrPattern<Nothing>, 
    NGTArrayOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing, Nothing>, 
    NGTAutoOrColorOrGradientOrNoneOrPattern, 
    NGTAutoOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing>
@Serializable
sealed interface NGTAutoOrColorOrGradientOrNoneOrPattern : NGTValue, 
    NGTAutoOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing>
@Serializable
sealed interface NGTAlignmentOrAuto : NGTValue, 
    NGTAlignmentOrAutoOrNone, 
    NGTAlignmentOrArrayOrAuto<Nothing>
@Serializable
sealed interface NGTAutoOrDictionaryOrRelative<out V : TValue> : NGTValue
@Serializable
sealed interface NGTFractionOrNoneOrRelative : NGTValue
@Serializable
sealed interface NGTContentOrFractionOrRelative : NGTValue
@Serializable
sealed interface NGTAutoOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<out V : TValue> : NGTValue
@Serializable
sealed interface NGTArrayOrNone<out E : TValue> : NGTValue, 
    NGTArrayOrColorOrGradientOrNoneOrPattern<E>, 
    NGTArrayOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<E, Nothing>, 
    NGTArrayOrAutoOrDictionaryOrNoneOrStr<E, Nothing>
@Serializable
sealed interface NGTAutoOrColorOrGradientOrPattern : NGTValue, 
    NGTAutoOrColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<Nothing>, 
    NGTAutoOrColorOrGradientOrNoneOrPattern, 
    NGTAutoOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing>
@Serializable
sealed interface NGTArrayOrAutoOrDictionaryOrNoneOrStr<out E : TValue, out V : TValue> : NGTValue
@Serializable
sealed interface NGTAutoOrFloat : NGTValue
