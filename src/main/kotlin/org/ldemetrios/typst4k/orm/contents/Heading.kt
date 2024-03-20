package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("heading")
@Serializable
data class THeading(
    @SerialName("level") val level : TAutoOrInt? = null, 
    @SerialName("depth") val depth : TInt? = null, 
    @SerialName("offset") val offset : TInt? = null, 
    @SerialName("numbering") val numbering : TNoneOrStr? = null, 
    @SerialName("supplement") val supplement : TAutoOrContentOrNone? = null, 
    @SerialName("outlined") val outlined : TBool? = null, 
    @SerialName("bookmarked") val bookmarked : TAutoOrBool? = null, 
    @SerialName("body") val body : TContent, 
) : TContent
{
    override fun repr() : String = RT.structRepr("heading", "level" to level, "depth" to depth, "offset" to offset, "numbering" to numbering, "supplement" to supplement, "outlined" to outlined, "bookmarked" to bookmarked, null to body, )
}
