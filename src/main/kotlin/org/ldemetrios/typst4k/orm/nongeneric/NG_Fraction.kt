@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("fraction")
@Serializable
data class NGTFraction(
    @SerialName("value") val value : NGTFloat, 
) : NGTValue, 
    NGTFractionOrRelative, 
    NGTArrayOrAutoOrFractionOrIntOrRelative<Nothing>, 
    NGTAutoOrFractionOrRelative, 
    NGTFractionOrNoneOrRelative, 
    NGTContentOrFractionOrRelative
{
    override fun convert() = TFraction(value?.convert().cast())
}
