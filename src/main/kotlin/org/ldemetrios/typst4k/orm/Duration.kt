package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("duration")
@Serializable
data class TDuration(
    @SerialName("seconds") val seconds : TInt? = null, 
    @SerialName("minutes") val minutes : TInt? = null, 
    @SerialName("hours") val hours : TInt? = null, 
    @SerialName("days") val days : TInt? = null, 
    @SerialName("weeks") val weeks : TInt? = null, 
) : TValue
{
    override fun repr() : String = RT.structRepr("duration", Triple(false, "seconds", seconds), Triple(false, "minutes", minutes), Triple(false, "hours", hours), Triple(false, "days", days), Triple(false, "weeks", weeks), )
}
