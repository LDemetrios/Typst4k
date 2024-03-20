@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("math.overbrace")
@Serializable
data class NGTOverbrace(
    @SerialName("body") val body : NGTContent, 
    @SerialName("annotation") val annotation : NGTNoneOrNonecontent? = null, 
) : NGTContent
{
    override fun convert() = TOverbrace(body?.convert().cast(), annotation?.convert().cast())
}
