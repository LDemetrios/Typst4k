@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("math.mat")
@Serializable
data class NGTMat(
    @SerialName("delim") val delim : NGTNoneOrStr? = null, 
    @SerialName("augment") val augment : NGTDictionaryOrIntOrNone<*, >? = null, 
    @SerialName("gap") val gap : NGTRelative? = null, 
    @SerialName("row-gap") val rowGap : NGTRelative? = null, 
    @SerialName("column-gap") val columnGap : NGTRelative? = null, 
    @SerialName("rows") val rows : NGTArray<TArray<TContent, >, >, 
) : NGTContent
{
    override fun convert() = TMat(delim?.convert().cast(), augment?.convert().cast(), gap?.convert().cast(), rowGap?.convert().cast(), columnGap?.convert().cast(), rows?.convert().cast())
}
