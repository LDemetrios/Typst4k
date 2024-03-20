@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("math.cancel")
@Serializable
data class NGTCancel(
    @SerialName("body") val body : NGTContent, 
    @SerialName("length") val length : NGTRelative? = null, 
    @SerialName("inverted") val inverted : NGTBool? = null, 
    @SerialName("cross") val cross : NGTBool? = null, 
    @SerialName("angle") val angle : NGTAngleOrAuto? = null, 
    @SerialName("stroke") val stroke : NGTColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<*, >? = null, 
) : NGTContent
{
    override fun convert() = TCancel(body?.convert().cast(), length?.convert().cast(), inverted?.convert().cast(), cross?.convert().cast(), angle?.convert().cast(), stroke?.convert().cast())
}
