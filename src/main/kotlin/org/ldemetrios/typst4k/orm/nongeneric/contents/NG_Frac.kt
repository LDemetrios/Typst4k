@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("math.frac")
@Serializable
data class NGTFrac(
    @SerialName("num") val num : NGTContent, 
    @SerialName("denom") val denom : NGTContent, 
) : NGTContent
{
    override fun convert() = TFrac(num?.convert().cast(), denom?.convert().cast())
}
