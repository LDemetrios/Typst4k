@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("linebreak")
@Serializable
data class NGTLinebreak(
    @SerialName("justify") val justify : NGTBool? = null, 
) : NGTContent
{
    override fun convert() = TLinebreak(justify?.convert().cast())
}
