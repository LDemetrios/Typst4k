package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("math.equation")
@Serializable
data class TEquation(
    @SerialName("block") val block : TBool? = null, 
    @SerialName("numbering") val numbering : TNoneOrStr? = null, 
    @SerialName("number-align") val numberAlign : TAlignment? = null, 
    @SerialName("supplement") val supplement : TAutoOrContentOrNone? = null, 
    @SerialName("body") val body : TContent, 
) : TContent
{
    override fun repr() : String = RT.structRepr("math.equation", "block" to block, "numbering" to numbering, "number-align" to numberAlign, "supplement" to supplement, null to body, )
}
