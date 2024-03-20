@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("cite")
@Serializable
data class NGTCite(
    @SerialName("key") val key : NGTLabel, 
    @SerialName("supplement") val supplement : NGTContentOrNone? = null, 
    @SerialName("form") val form : NGTNoneOrStr? = null, 
    @SerialName("style") val style : NGTAutoOrStr? = null, 
) : NGTContent
{
    override fun convert() = TCite(key?.convert().cast(), supplement?.convert().cast(), form?.convert().cast(), style?.convert().cast())
}
