package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("type")
@Serializable
data class TType(
    @SerialName("value") val value : TStr? = null, 
) : TValue
{
    override fun repr() : String = RT.structRepr("type", "value" to value, )
}
