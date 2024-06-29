package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("place")
@Serializable
data class TPlace(
    @SerialName("alignment") val alignment : TAlignmentOrAuto? = null, 
    @SerialName("body") val body : TContent, 
    @SerialName("float") val float : TBool? = null, 
    @SerialName("clearance") val clearance : TLength? = null, 
    @SerialName("dx") val dx : TRelative? = null, 
    @SerialName("dy") val dy : TRelative? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("place", Triple(false, null, alignment), Triple(false, null, body), Triple(false, "float", float), Triple(false, "clearance", clearance), Triple(false, "dx", dx), Triple(false, "dy", dy), )
}
