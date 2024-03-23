package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("lr")
@Serializable
data class TLr(
    @SerialName("size") val size : TAutoOrRelative? = null, 
    @SerialName("body") val body : TContent, 
) : TContent
{
    override fun repr() : String = RT.structRepr("math.lr", Triple(false, "size", size), Triple(false, null, body), )
}
