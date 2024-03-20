package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("pad")
@Serializable
data class TPad(
    @SerialName("left") val left : TRelative? = null, 
    @SerialName("top") val top : TRelative? = null, 
    @SerialName("right") val right : TRelative? = null, 
    @SerialName("bottom") val bottom : TRelative? = null, 
    @SerialName("x") val x : TRelative? = null, 
    @SerialName("y") val y : TRelative? = null, 
    @SerialName("rest") val rest : TRelative? = null, 
    @SerialName("body") val body : TContent, 
) : TContent
{
    override fun repr() : String = RT.structRepr("pad", "left" to left, "top" to top, "right" to right, "bottom" to bottom, "x" to x, "y" to y, "rest" to rest, null to body, )
}
