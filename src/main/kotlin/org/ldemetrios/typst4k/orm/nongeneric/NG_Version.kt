@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("version")
@Serializable
data class NGTVersion(
    @SerialName("value") val value : NGTArray<TInt, >, 
) : NGTValue
{
    override fun convert() = TVersion(value?.convert().cast())
}
