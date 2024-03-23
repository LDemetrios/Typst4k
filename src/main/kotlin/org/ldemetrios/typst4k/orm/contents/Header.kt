package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("grid.header")
@Serializable
data class THeader(
    @SerialName("repeat") val repeat : TBool? = null, 
    @SerialName("children") val children : TArray<TContent, >, 
) : TContent
{
    override fun repr() : String = RT.structRepr("grid.header", Triple(false, "repeat", repeat), Triple(false, null, children), )
}
