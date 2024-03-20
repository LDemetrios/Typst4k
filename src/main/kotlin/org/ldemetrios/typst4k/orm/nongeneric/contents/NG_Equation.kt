@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("math.equation")
@Serializable
data class NGTEquation(
    @SerialName("block") val block : NGTBool? = null, 
    @SerialName("numbering") val numbering : NGTNoneOrStr? = null, 
    @SerialName("number-align") val numberAlign : NGTAlignment? = null, 
    @SerialName("supplement") val supplement : NGTAutoOrContentOrNone? = null, 
    @SerialName("body") val body : NGTContent, 
) : NGTContent
{
    override fun convert() = TEquation(block?.convert().cast(), numbering?.convert().cast(), numberAlign?.convert().cast(), supplement?.convert().cast(), body?.convert().cast())
}
