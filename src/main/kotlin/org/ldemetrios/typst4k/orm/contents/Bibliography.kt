package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("bibliography")
@Serializable
data class TBibliography(
    @SerialName("path") val path : TArrayOrStr<TStr, >, 
    @SerialName("title") val title : TAutoOrContentOrNone? = null, 
    @SerialName("full") val full : TBool? = null, 
    @SerialName("style") val style : TStr? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("bibliography", Triple(false, null, path), Triple(false, "title", title), Triple(false, "full", full), Triple(false, "style", style), )
}
