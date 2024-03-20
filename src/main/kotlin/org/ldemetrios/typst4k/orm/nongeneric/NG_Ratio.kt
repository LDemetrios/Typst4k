@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("ratio")
@Serializable
data class NGTRatio(
    @SerialName("value") val value : NGTFloat, 
) : NGTValue, 
    NGTIntOrRatio, 
    NGTFloatOrRatio, 
    NGTColorOrRatio
{
    override fun convert() = TRatio(value?.convert().cast())
}
