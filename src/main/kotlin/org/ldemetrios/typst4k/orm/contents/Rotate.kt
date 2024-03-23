package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("rotate")
@Serializable
data class TRotate(
    @SerialName("pos") val pos : TAngle? = null, 
    @SerialName("origin") val origin : TAlignment? = null, 
    @SerialName("reflow") val reflow : TBool? = null, 
    @SerialName("body") val body : TContent, 
) : TContent
{
    override fun repr() : String = RT.structRepr("rotate", Triple(false, "pos", pos), Triple(false, "origin", origin), Triple(false, "reflow", reflow), Triple(false, null, body), )
}
