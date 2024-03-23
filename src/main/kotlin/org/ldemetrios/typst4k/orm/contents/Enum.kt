package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("enum")
@Serializable
data class TEnum(
    @SerialName("tight") val tight : TBool? = null, 
    @SerialName("numbering") val numbering : TStr? = null, 
    @SerialName("start") val start : TInt? = null, 
    @SerialName("full") val full : TBool? = null, 
    @SerialName("indent") val indent : TLength? = null, 
    @SerialName("body-indent") val bodyIndent : TLength? = null, 
    @SerialName("spacing") val spacing : TAutoOrFractionOrRelative? = null, 
    @SerialName("number-align") val numberAlign : TAlignment? = null, 
    @SerialName("children") val children : TArray<TArrayOrContent<*, >, >? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("enum", Triple(false, "tight", tight), Triple(false, "numbering", numbering), Triple(false, "start", start), Triple(false, "full", full), Triple(false, "indent", indent), Triple(false, "body-indent", bodyIndent), Triple(false, "spacing", spacing), Triple(false, "number-align", numberAlign), Triple(false, "children", children), )
}
