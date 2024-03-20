@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("heading")
@Serializable
data class NGTHeading(
    @SerialName("level") val level : NGTAutoOrInt? = null, 
    @SerialName("depth") val depth : NGTInt? = null, 
    @SerialName("offset") val offset : NGTInt? = null, 
    @SerialName("numbering") val numbering : NGTNoneOrStr? = null, 
    @SerialName("supplement") val supplement : NGTAutoOrContentOrNone? = null, 
    @SerialName("outlined") val outlined : NGTBool? = null, 
    @SerialName("bookmarked") val bookmarked : NGTAutoOrBool? = null, 
    @SerialName("body") val body : NGTContent, 
) : NGTContent
{
    override fun convert() = THeading(level?.convert().cast(), depth?.convert().cast(), offset?.convert().cast(), numbering?.convert().cast(), supplement?.convert().cast(), outlined?.convert().cast(), bookmarked?.convert().cast(), body?.convert().cast())
}
