@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("math.vec")
@Serializable
data class NGTVec(
    @SerialName("delim") val delim : NGTNoneOrStr? = null, 
    @SerialName("gap") val gap : NGTRelative? = null, 
    @SerialName("children") val children : NGTArray<TContent, >, 
) : NGTContent
{
    override fun convert() = TVec(delim?.convert().cast(), gap?.convert().cast(), children?.convert().cast())
}
