@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("h")
@Serializable
data class NGTH(
    @SerialName("amount") val amount : NGTFractionOrRelative, 
    @SerialName("weak") val weak : NGTBool? = null, 
) : NGTContent
{
    override fun convert() = TH(amount?.convert().cast(), weak?.convert().cast())
}
