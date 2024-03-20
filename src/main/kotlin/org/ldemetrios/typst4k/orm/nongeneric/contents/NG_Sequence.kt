@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("sequence")
@Serializable
data class NGTSequence(
    @SerialName("children") val children : NGTArray<TContent, >, 
) : NGTContent
{
    override fun convert() = TSequence(children?.convert().cast())
}
