package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("relative")
@Serializable
data class TRelative(
    @SerialName("rel") val rel : TRatio? = null, 
    @SerialName("abs") val abs : TLength? = null, 
) : TValue, 
    TFractionOrRelative, 
    TDictionaryOrRelative<Nothing>, 
    TAutoOrBoolOrNoneOrRelative, 
    TArrayOrAutoOrFractionOrIntOrRelative<Nothing>, 
    TArrayOrDictionaryOrRelative<Nothing, Nothing>, 
    TAutoOrFractionOrRelative, 
    TAutoOrRelative, 
    TAutoOrDictionaryOrRelative<Nothing>, 
    TFractionOrNoneOrRelative, 
    TContentOrFractionOrRelative
{
    override fun repr() : String = RT.structRepr("relative", "rel" to rel, "abs" to abs, )
}
