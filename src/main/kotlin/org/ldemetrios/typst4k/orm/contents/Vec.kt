package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("vec")
@Serializable
data class TVec(
    @SerialName("delim") val delim : TNoneOrStr? = null, 
    @SerialName("gap") val gap : TRelative? = null, 
    @SerialName("children") val children : TArray<TContent, >, 
) : TContent
{
    override fun repr() : String = RT.structRepr("math.vec", Triple(false, "delim", delim), Triple(false, "gap", gap), Triple(false, null, children), )
}
