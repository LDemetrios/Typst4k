@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("direction")
@Serializable
data class NGTDirection(
    @SerialName("value") val value : NGTStr? = null, 
) : NGTValue, 
    NGTAutoOrDirection
{
    override fun convert() = TDirection(value?.convert().cast())
}
