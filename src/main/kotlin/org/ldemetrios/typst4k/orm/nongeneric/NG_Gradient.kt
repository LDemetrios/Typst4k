@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@Serializable
sealed interface NGTGradient : NGTValue, 
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
