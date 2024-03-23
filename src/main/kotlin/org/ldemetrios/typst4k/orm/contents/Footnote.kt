package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("footnote")
@Serializable
data class TFootnote(
    @SerialName("numbering") val numbering : TStr? = null, 
    @SerialName("body") val body : TContentOrLabel, 
) : TContent
{
    override fun repr() : String = RT.structRepr("footnote", Triple(false, "numbering", numbering), Triple(false, null, body), )
}
