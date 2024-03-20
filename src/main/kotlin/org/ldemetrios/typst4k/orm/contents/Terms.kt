package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("terms")
@Serializable
data class TTerms(
    @SerialName("tight") val tight : TBool? = null, 
    @SerialName("separator") val separator : TContent? = null, 
    @SerialName("indent") val indent : TLength? = null, 
    @SerialName("hanging-indent") val hangingIndent : TLength? = null, 
    @SerialName("spacing") val spacing : TAutoOrFractionOrRelative? = null, 
    @SerialName("children") val children : TArray<TArrayOrContent<*, >, >? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("terms", "tight" to tight, "separator" to separator, "indent" to indent, "hanging-indent" to hangingIndent, "spacing" to spacing, "children" to children, )
}
