@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("enum")
@Serializable
data class NGTEnum(
    @SerialName("tight") val tight : NGTBool? = null, 
    @SerialName("numbering") val numbering : NGTStr? = null, 
    @SerialName("start") val start : NGTInt? = null, 
    @SerialName("full") val full : NGTBool? = null, 
    @SerialName("indent") val indent : NGTLength? = null, 
    @SerialName("body-indent") val bodyIndent : NGTLength? = null, 
    @SerialName("spacing") val spacing : NGTAutoOrFractionOrRelative? = null, 
    @SerialName("number-align") val numberAlign : NGTAlignment? = null, 
    @SerialName("children") val children : NGTArray<TArrayOrContent<*, >, >? = null, 
) : NGTContent
{
    override fun convert() = TEnum(tight?.convert().cast(), numbering?.convert().cast(), start?.convert().cast(), full?.convert().cast(), indent?.convert().cast(), bodyIndent?.convert().cast(), spacing?.convert().cast(), numberAlign?.convert().cast(), children?.convert().cast())
}
