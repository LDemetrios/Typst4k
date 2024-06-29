package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("heading")
@Serializable
data class THeading(
    @SerialName("body") val body : TContent, 
    @SerialName("level") val level : TAutoOrInt? = null, 
    @SerialName("depth") val depth : TInt? = null, 
    @SerialName("offset") val offset : TInt? = null, 
    @SerialName("numbering") val numbering : TNoneOrStr? = null, 
    @SerialName("supplement") val supplement : TAutoOrContentOrNone? = null, 
    @SerialName("outlined") val outlined : TBool? = null, 
    @SerialName("bookmarked") val bookmarked : TAutoOrBool? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("heading", Triple(false, null, body), Triple(false, "level", level), Triple(false, "depth", depth), Triple(false, "offset", offset), Triple(false, "numbering", numbering), Triple(false, "supplement", supplement), Triple(false, "outlined", outlined), Triple(false, "bookmarked", bookmarked), )
}
