package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("figure")
@Serializable
data class TFigure(
    @SerialName("body") val body : TContent, 
    @SerialName("placement") val placement : TAlignmentOrAutoOrNone? = null, 
    @SerialName("caption") val caption : TContentOrNone? = null, 
    @SerialName("kind") val kind : TAutoOrStr? = null, 
    @SerialName("supplement") val supplement : TAutoOrContentOrNone? = null, 
    @SerialName("numbering") val numbering : TNoneOrStr? = null, 
    @SerialName("gap") val gap : TLength? = null, 
    @SerialName("outlined") val outlined : TBool? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("figure", Triple(false, null, body), Triple(false, "placement", placement), Triple(false, "caption", caption), Triple(false, "kind", kind), Triple(false, "supplement", supplement), Triple(false, "numbering", numbering), Triple(false, "gap", gap), Triple(false, "outlined", outlined), )
}
