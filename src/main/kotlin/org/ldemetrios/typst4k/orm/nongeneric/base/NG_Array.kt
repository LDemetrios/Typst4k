@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("array")
@Serializable
data class NGTArray<out E : TValue>(val value : List<NGTValue>) : NGTValue, List<NGTValue> by value, 
    NGTArrayOrColor<E>, 
    NGTArrayOrAuto<E>, 
    NGTArrayOrStr<E>, 
    NGTArrayOrAutoOrDictionaryOrStr<E, Nothing>, 
    NGTArrayOrAutoOrFractionOrIntOrRelative<E>, 
    NGTArrayOrColorOrGradientOrNoneOrPattern<E>, 
    NGTAlignmentOrArrayOrAuto<E>, 
    NGTArrayOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<E, Nothing>, 
    NGTArrayOrDictionaryOrRelative<E, Nothing>, 
    NGTArrayOrDictionary<E, Nothing>, 
    NGTArrayOrContent<E>, 
    NGTArrayOrNone<E>, 
    NGTArrayOrAutoOrDictionaryOrNoneOrStr<E, Nothing> {
    override fun convert() = RT.convertArray<E>(value)
}
