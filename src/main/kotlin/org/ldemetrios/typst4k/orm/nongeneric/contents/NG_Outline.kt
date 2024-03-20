@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("outline")
@Serializable
data class NGTOutline(
    @SerialName("title") val title : NGTAutoOrContentOrNone? = null, 
    @SerialName("target") val target : NGTLabelOrLocationOrSelector? = null, 
    @SerialName("depth") val depth : NGTIntOrNone? = null, 
    @SerialName("indent") val indent : NGTAutoOrBoolOrNoneOrRelative? = null, 
    @SerialName("fill") val fill : NGTContentOrNone? = null, 
) : NGTContent
{
    override fun convert() = TOutline(title?.convert().cast(), target?.convert().cast(), depth?.convert().cast(), indent?.convert().cast(), fill?.convert().cast())
}
