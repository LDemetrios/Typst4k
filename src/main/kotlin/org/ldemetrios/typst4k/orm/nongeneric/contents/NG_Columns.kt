@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("columns")
@Serializable
data class NGTColumns(
    @SerialName("count") val count : NGTInt? = null, 
    @SerialName("gutter") val gutter : NGTRelative? = null, 
    @SerialName("body") val body : NGTContent, 
) : NGTContent
{
    override fun convert() = TColumns(count?.convert().cast(), gutter?.convert().cast(), body?.convert().cast())
}
