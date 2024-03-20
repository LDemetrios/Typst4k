@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("duration")
@Serializable
data class NGTDuration(
    @SerialName("seconds") val seconds : NGTInt? = null, 
    @SerialName("minutes") val minutes : NGTInt? = null, 
    @SerialName("hours") val hours : NGTInt? = null, 
    @SerialName("days") val days : NGTInt? = null, 
    @SerialName("weeks") val weeks : NGTInt? = null, 
) : NGTValue
{
    override fun convert() = TDuration(seconds?.convert().cast(), minutes?.convert().cast(), hours?.convert().cast(), days?.convert().cast(), weeks?.convert().cast())
}
