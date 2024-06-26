package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("datetime")
@Serializable
data class TDatetime(
    @SerialName("year") val year : TInt? = null, 
    @SerialName("month") val month : TInt? = null, 
    @SerialName("day") val day : TInt? = null, 
    @SerialName("hour") val hour : TInt? = null, 
    @SerialName("minute") val minute : TInt? = null, 
    @SerialName("second") val second : TInt? = null, 
) : TValue, 
    TAutoOrDatetimeOrNone
{
    override fun repr() : String = RT.structRepr("datetime", Triple(false, "year", year), Triple(false, "month", month), Triple(false, "day", day), Triple(false, "hour", hour), Triple(false, "minute", minute), Triple(false, "second", second), )
}
