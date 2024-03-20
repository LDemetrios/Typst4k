@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("math.cases")
@Serializable
data class NGTCases(
    @SerialName("delim") val delim : NGTStr? = null, 
    @SerialName("reverse") val reverse : NGTBool? = null, 
    @SerialName("gap") val gap : NGTRelative? = null, 
    @SerialName("children") val children : NGTArray<TContent, >, 
) : NGTContent
{
    override fun convert() = TCases(delim?.convert().cast(), reverse?.convert().cast(), gap?.convert().cast(), children?.convert().cast())
}
