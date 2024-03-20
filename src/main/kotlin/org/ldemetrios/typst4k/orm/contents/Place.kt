package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("place")
@Serializable
data class TPlace(
    @SerialName("alignment") val alignment : TAlignmentOrAuto? = null, 
    @SerialName("float") val float : TBool? = null, 
    @SerialName("clearance") val clearance : TLength? = null, 
    @SerialName("dx") val dx : TRelative? = null, 
    @SerialName("dy") val dy : TRelative? = null, 
    @SerialName("body") val body : TContent, 
) : TContent
{
    override fun repr() : String = RT.structRepr("place", null to alignment, "float" to float, "clearance" to clearance, "dx" to dx, "dy" to dy, null to body, )
}
