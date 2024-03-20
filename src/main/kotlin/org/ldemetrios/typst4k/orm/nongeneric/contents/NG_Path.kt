@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("path")
@Serializable
data class NGTPath(
    @SerialName("fill") val fill : NGTColorOrGradientOrNoneOrPattern? = null, 
    @SerialName("stroke") val stroke : NGTAutoOrColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<*, >? = null, 
    @SerialName("closed") val closed : NGTBool? = null, 
    @SerialName("vertices") val vertices : NGTArray<TArray<*, >, >, 
) : NGTContent
{
    override fun convert() = TPath(fill?.convert().cast(), stroke?.convert().cast(), closed?.convert().cast(), vertices?.convert().cast())
}
