@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("colbreak")
@Serializable
data class NGTColbreak(
    @SerialName("weak") val weak : NGTBool? = null, 
) : NGTContent
{
    override fun convert() = TColbreak(weak?.convert().cast())
}
