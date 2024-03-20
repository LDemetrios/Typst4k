@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("module")
@Serializable
data class NGTModule(
    @SerialName("name") val name : NGTStr, 
) : NGTValue
{
    override fun convert() = TModule(name?.convert().cast())
}
