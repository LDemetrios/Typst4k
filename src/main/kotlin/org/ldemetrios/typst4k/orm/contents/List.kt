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
    override fun repr() : String = RT.structRepr("list", Triple(false, "tight", tight), Triple(false, "marker", marker), Triple(false, "indent", indent), Triple(false, "body-indent", bodyIndent), Triple(false, "spacing", spacing), Triple(false, "children", children), )
}
