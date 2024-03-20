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
    override fun repr() : String = RT.structRepr("scale", "x" to x, "y" to y, "origin" to origin, "reflow" to reflow, null to body, )
}
