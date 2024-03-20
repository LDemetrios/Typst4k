@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("metadata")
@Serializable
data class NGTMetadata<out D : TValue>(
    @SerialName("value") val value : NGTValue, 
) : NGTContent
{
    override fun convert() = RT.convertMetadata<D>(this)
}
