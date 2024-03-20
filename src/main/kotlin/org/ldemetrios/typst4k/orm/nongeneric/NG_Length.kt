@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("length")
@Serializable
data class NGTLength(
    @SerialName("pts") val pts : NGTFloat? = null, 
    @SerialName("em") val em : NGTFloat? = null, 
) : NGTValue, 
    NGTColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing>, 
    NGTLengthOrStr, 
    NGTIntOrLength, 
    NGTAutoOrColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<Nothing>, 
    NGTAutoOrLength, 
    NGTArrayOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing, Nothing>, 
    NGTColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<Nothing>, 
    NGTAutoOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing>
{
    override fun convert() = TLength(pts?.convert().cast(), em?.convert().cast())
}
