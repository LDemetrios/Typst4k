package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("move")
@Serializable
data class TMove(
    @SerialName("dx") val dx : TRelative? = null, 
    @SerialName("dy") val dy : TRelative? = null, 
    @SerialName("body") val body : TContent, 
) : TContent
{
    override fun repr() : String = RT.structRepr("move", Triple(false, "dx", dx), Triple(false, "dy", dy), Triple(false, null, body), )
}
