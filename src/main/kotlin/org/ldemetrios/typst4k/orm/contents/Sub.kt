package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("sub")
@Serializable
data class TSub(
    @SerialName("typographic") val typographic : TBool? = null, 
    @SerialName("baseline") val baseline : TLength? = null, 
    @SerialName("size") val size : TLength? = null, 
    @SerialName("body") val body : TContent, 
) : TContent
{
    override fun repr() : String = RT.structRepr("sub", "typographic" to typographic, "baseline" to baseline, "size" to size, null to body, )
}
