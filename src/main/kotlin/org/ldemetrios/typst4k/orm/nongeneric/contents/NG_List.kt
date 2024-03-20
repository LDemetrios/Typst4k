@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("list")
@Serializable
data class NGTList(
    @SerialName("tight") val tight : NGTBool? = null, 
    @SerialName("marker") val marker : NGTArrayOrContent<TContent, >? = null, 
    @SerialName("indent") val indent : NGTLength? = null, 
    @SerialName("body-indent") val bodyIndent : NGTLength? = null, 
    @SerialName("spacing") val spacing : NGTAutoOrFractionOrRelative? = null, 
    @SerialName("children") val children : NGTArray<TItem, >? = null, 
) : NGTContent
{
    override fun convert() = TList(tight?.convert().cast(), marker?.convert().cast(), indent?.convert().cast(), bodyIndent?.convert().cast(), spacing?.convert().cast(), children?.convert().cast())
}
