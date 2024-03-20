@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("type")
@Serializable
data class NGTType(
    @SerialName("value") val value : NGTStr? = null, 
) : NGTValue
{
    override fun convert() = TType(value?.convert().cast())
}
