@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("relative")
@Serializable
data class NGTRelative(
    @SerialName("rel") val rel : NGTRatio? = null, 
    @SerialName("abs") val abs : NGTLength? = null, 
) : NGTValue, 
    NGTFractionOrRelative, 
    NGTDictionaryOrRelative<Nothing>, 
    NGTAutoOrBoolOrNoneOrRelative, 
    NGTArrayOrAutoOrFractionOrIntOrRelative<Nothing>, 
    NGTArrayOrDictionaryOrRelative<Nothing, Nothing>, 
    NGTAutoOrFractionOrRelative, 
    NGTAutoOrRelative, 
    NGTAutoOrDictionaryOrRelative<Nothing>, 
    NGTFractionOrNoneOrRelative, 
    NGTContentOrFractionOrRelative
{
    override fun convert() = TRelative(rel?.convert().cast(), abs?.convert().cast())
}
