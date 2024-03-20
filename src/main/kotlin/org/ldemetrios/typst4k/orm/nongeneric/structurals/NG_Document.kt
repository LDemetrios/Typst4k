@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("document")
@Serializable
data class NGTDocument(
    @SerialName("title") val title : NGTContentOrNone? = null, 
    @SerialName("author") val author : NGTArrayOrStr<TStr, >? = null, 
    @SerialName("keywords") val keywords : NGTArrayOrStr<TStr, >? = null, 
    @SerialName("date") val date : NGTAutoOrDatetimeOrNone? = null, 
) : NGTStructural
{
    override fun convert() = TDocument(title?.convert().cast(), author?.convert().cast(), keywords?.convert().cast(), date?.convert().cast())
}
