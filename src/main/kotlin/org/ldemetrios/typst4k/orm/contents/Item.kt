package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("item")
@Serializable
data class TItem(
    @SerialName("number") val number : TIntOrNone? = null, 
    @SerialName("body") val body : TContent? = null, 
    @SerialName("term") val term : TContent? = null, 
    @SerialName("description") val description : TContent? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("item", Triple(false, null, number), Triple(false, null, body), Triple(false, null, term), Triple(false, null, description), )
}
