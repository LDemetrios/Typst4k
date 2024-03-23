package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("mat")
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
    override fun repr() : String = RT.structRepr("math.mat", Triple(false, "delim", delim), Triple(false, "augment", augment), Triple(false, "gap", gap), Triple(false, "row-gap", rowGap), Triple(false, "column-gap", columnGap), Triple(false, null, rows), )
}
