@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("bibliography")
@Serializable
data class NGTBibliography(
    @SerialName("path") val path : NGTArrayOrStr<TStr, >, 
    @SerialName("title") val title : NGTAutoOrContentOrNone? = null, 
    @SerialName("full") val full : NGTBool? = null, 
    @SerialName("style") val style : NGTStr? = null, 
) : NGTContent
{
    override fun convert() = TBibliography(path?.convert().cast(), title?.convert().cast(), full?.convert().cast(), style?.convert().cast())
}
