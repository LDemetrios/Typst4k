package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("list")
@Serializable
data class TList(
    @SerialName("tight") val tight : TBool? = null, 
    @SerialName("marker") val marker : TArrayOrContent<TContent, >? = null, 
    @SerialName("indent") val indent : TLength? = null, 
    @SerialName("body-indent") val bodyIndent : TLength? = null, 
    @SerialName("spacing") val spacing : TAutoOrFractionOrRelative? = null, 
    @SerialName("children") val children : TArray<TItem, >? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("list", "tight" to tight, "marker" to marker, "indent" to indent, "body-indent" to bodyIndent, "spacing" to spacing, "children" to children, )
}
