@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("ref")
@Serializable
data class NGTRef(
    @SerialName("target") val target : NGTLabel, 
    @SerialName("supplement") val supplement : NGTAutoOrContentOrNone? = null, 
) : NGTContent
{
    override fun convert() = TRef(target?.convert().cast(), supplement?.convert().cast())
}
