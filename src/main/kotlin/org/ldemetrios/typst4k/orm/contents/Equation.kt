package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("equation")
@Serializable
data class TEquation(
    @SerialName("block") val block : TBool? = null, 
    @SerialName("numbering") val numbering : TNoneOrStr? = null, 
    @SerialName("number-align") val numberAlign : TAlignment? = null, 
    @SerialName("supplement") val supplement : TAutoOrContentOrNone? = null, 
    @SerialName("body") val body : TContent, 
) : TContent
{
    override fun repr() : String = RT.structRepr("math.equation", Triple(false, "block", block), Triple(false, "numbering", numbering), Triple(false, "number-align", numberAlign), Triple(false, "supplement", supplement), Triple(false, null, body), )
}
