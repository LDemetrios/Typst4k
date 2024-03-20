@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("datetime")
@Serializable
data class NGTDatetime(
    @SerialName("year") val year : NGTInt? = null, 
    @SerialName("month") val month : NGTInt? = null, 
    @SerialName("day") val day : NGTInt? = null, 
    @SerialName("hour") val hour : NGTInt? = null, 
    @SerialName("minute") val minute : NGTInt? = null, 
    @SerialName("second") val second : NGTInt? = null, 
) : NGTValue, 
    NGTAutoOrDatetimeOrNone
{
    override fun convert() = TDatetime(year?.convert().cast(), month?.convert().cast(), day?.convert().cast(), hour?.convert().cast(), minute?.convert().cast(), second?.convert().cast())
}
