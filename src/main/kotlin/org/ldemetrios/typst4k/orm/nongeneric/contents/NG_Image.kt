@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("image")
@Serializable
data class NGTImage(
    @SerialName("path") val path : NGTStr, 
    @SerialName("format") val format : NGTAutoOrStr? = null, 
    @SerialName("width") val width : NGTAutoOrRelative? = null, 
    @SerialName("height") val height : NGTAutoOrRelative? = null, 
    @SerialName("alt") val alt : NGTNoneOrStr? = null, 
    @SerialName("fit") val fit : NGTStr? = null, 
) : NGTContent
{
    override fun convert() = TImage(path?.convert().cast(), format?.convert().cast(), width?.convert().cast(), height?.convert().cast(), alt?.convert().cast(), fit?.convert().cast())
}
