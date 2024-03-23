package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("cases")
@Serializable
data class TCases(
    @SerialName("delim") val delim : TStr? = null, 
    @SerialName("reverse") val reverse : TBool? = null, 
    @SerialName("gap") val gap : TRelative? = null, 
    @SerialName("children") val children : TArray<TContent, >, 
) : TContent
{
    override fun repr() : String = RT.structRepr("math.cases", Triple(false, "delim", delim), Triple(false, "reverse", reverse), Triple(false, "gap", gap), Triple(false, null, children), )
}
