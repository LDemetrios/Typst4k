@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("regex")
@Serializable
data class NGTRegex(
    @SerialName("regex") val regex : NGTStr? = null, 
) : NGTValue
{
    override fun convert() = TRegex(regex?.convert().cast())
}
