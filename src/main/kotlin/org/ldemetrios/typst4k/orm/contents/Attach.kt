package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("attach")
@Serializable
data class TAttach(
    @SerialName("base") val base : TContent, 
    @SerialName("t") val t : TContentOrNone? = null, 
    @SerialName("b") val b : TContentOrNone? = null, 
    @SerialName("tl") val tl : TContentOrNone? = null, 
    @SerialName("bl") val bl : TContentOrNone? = null, 
    @SerialName("tr") val tr : TContentOrNone? = null, 
    @SerialName("br") val br : TContentOrNone? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("math.attach", Triple(false, null, base), Triple(false, "t", t), Triple(false, "b", b), Triple(false, "tl", tl), Triple(false, "bl", bl), Triple(false, "tr", tr), Triple(false, "br", br), )
}
