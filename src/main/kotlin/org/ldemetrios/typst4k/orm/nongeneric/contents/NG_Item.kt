@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("item")
@Serializable
data class NGTItem(
    @SerialName("number") val number : NGTIntOrNone? = null, 
    @SerialName("body") val body : NGTContent? = null, 
    @SerialName("term") val term : NGTContent? = null, 
    @SerialName("description") val description : NGTContent? = null, 
) : NGTContent
{
    override fun convert() = TItem(number?.convert().cast(), body?.convert().cast(), term?.convert().cast(), description?.convert().cast())
}
