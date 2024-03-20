@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("terms")
@Serializable
data class NGTTerms(
    @SerialName("tight") val tight : NGTBool? = null, 
    @SerialName("separator") val separator : NGTContent? = null, 
    @SerialName("indent") val indent : NGTLength? = null, 
    @SerialName("hanging-indent") val hangingIndent : NGTLength? = null, 
    @SerialName("spacing") val spacing : NGTAutoOrFractionOrRelative? = null, 
    @SerialName("children") val children : NGTArray<TArrayOrContent<*, >, >? = null, 
) : NGTContent
{
    override fun convert() = TTerms(tight?.convert().cast(), separator?.convert().cast(), indent?.convert().cast(), hangingIndent?.convert().cast(), spacing?.convert().cast(), children?.convert().cast())
}
