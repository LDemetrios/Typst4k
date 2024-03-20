@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("label")
@Serializable
data class NGTLabel(
    @SerialName("name") val name : NGTStr, 
) : NGTValue, 
    NGTContentOrLabel, 
    NGTDictionaryOrLabelOrLocationOrStr<Nothing>, 
    NGTLabelOrLocationOrSelector, 
    NGTContentOrLabelOrNone
{
    override fun convert() = TLabel(name?.convert().cast())
}
