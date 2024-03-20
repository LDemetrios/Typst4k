@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("smartquote")
@Serializable
data class NGTSmartquote(
    @SerialName("double") val double : NGTBool? = null, 
    @SerialName("enabled") val enabled : NGTBool? = null, 
    @SerialName("alternative") val alternative : NGTBool? = null, 
    @SerialName("quotes") val quotes : NGTArrayOrAutoOrDictionaryOrStr<*, *, >? = null, 
) : NGTContent
{
    override fun convert() = TSmartquote(double?.convert().cast(), enabled?.convert().cast(), alternative?.convert().cast(), quotes?.convert().cast())
}
