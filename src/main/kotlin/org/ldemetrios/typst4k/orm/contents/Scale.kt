package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("scale")
@Serializable
data class TScale(
    @SerialName("x") val x : TRatio? = null, 
    @SerialName("y") val y : TRatio? = null, 
    @SerialName("origin") val origin : TAlignment? = null, 
    @SerialName("reflow") val reflow : TBool? = null, 
    @SerialName("body") val body : TContent, 
) : TContent
{
    override fun repr() : String = RT.structRepr("scale", Triple(false, "x", x), Triple(false, "y", y), Triple(false, "origin", origin), Triple(false, "reflow", reflow), Triple(false, null, body), )
}
