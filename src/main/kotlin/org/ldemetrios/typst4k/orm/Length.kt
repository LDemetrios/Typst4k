package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("length")
@Serializable
data class TLength(
    @SerialName("pts") val pts : TFloat? = null, 
    @SerialName("em") val em : TFloat? = null, 
) : TValue, 
    TColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing>, 
    TLengthOrStr, 
    TIntOrLength, 
    TAutoOrColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<Nothing>, 
    TAutoOrLength, 
    TArrayOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing, Nothing>, 
    TColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<Nothing>, 
    TAutoOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<Nothing>
{
    override fun repr() : String = RT.reprOf(this)
}
