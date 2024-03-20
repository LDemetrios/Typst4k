package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("math.mat")
@Serializable
data class TMat(
    @SerialName("delim") val delim : TNoneOrStr? = null, 
    @SerialName("augment") val augment : TDictionaryOrIntOrNone<*, >? = null, 
    @SerialName("gap") val gap : TRelative? = null, 
    @SerialName("row-gap") val rowGap : TRelative? = null, 
    @SerialName("column-gap") val columnGap : TRelative? = null, 
    @SerialName("rows") val rows : TArray<TArray<TContent, >, >, 
) : TContent
{
    override fun repr() : String = RT.structRepr("math.mat", "delim" to delim, "augment" to augment, "gap" to gap, "row-gap" to rowGap, "column-gap" to columnGap, null to rows, )
}
