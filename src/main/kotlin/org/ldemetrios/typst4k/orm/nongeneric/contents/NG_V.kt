@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("v")
@Serializable
data class NGTV(
    @SerialName("amount") val amount : NGTFractionOrRelative, 
    @SerialName("weak") val weak : NGTBool? = null, 
) : NGTContent
{
    override fun convert() = TV(amount?.convert().cast(), weak?.convert().cast())
}
