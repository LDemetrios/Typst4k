@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("dictionary")
@Serializable
data class NGTDictionary<out V : TValue>(val value : Map<String, NGTValue>) : NGTValue, Map<String, NGTValue> by value, 
    NGTColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<V>, 
    NGTDictionaryOrRelative<V>, 
    NGTDictionaryOrLabelOrLocationOrStr<V>, 
    NGTAutoOrColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<V>, 
    NGTArrayOrAutoOrDictionaryOrStr<Nothing, V>, 
    NGTArrayOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing, V>, 
    NGTArrayOrDictionaryOrRelative<Nothing, V>, 
    NGTArrayOrDictionary<Nothing, V>, 
    NGTColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<V>, 
    NGTDictionaryOrIntOrNone<V>, 
    NGTAutoOrDictionaryOrRelative<V>, 
    NGTAutoOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<V>, 
    NGTArrayOrAutoOrDictionaryOrNoneOrStr<Nothing, V> {
    override fun convert() = RT.convertDictionary<V>(value)
}
