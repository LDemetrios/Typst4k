package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("strong")
@Serializable
data class TStrong(
    @SerialName("delta") val delta : TInt? = null, 
    @SerialName("body") val body : TContent, 
) : TContent
{
    override fun repr() : String = RT.structRepr("strong", "delta" to delta, null to body, )
}
