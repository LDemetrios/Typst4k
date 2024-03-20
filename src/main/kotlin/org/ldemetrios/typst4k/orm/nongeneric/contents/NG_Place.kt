@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("place")
@Serializable
data class NGTPlace(
    @SerialName("alignment") val alignment : NGTAlignmentOrAuto? = null, 
    @SerialName("float") val float : NGTBool? = null, 
    @SerialName("clearance") val clearance : NGTLength? = null, 
    @SerialName("dx") val dx : NGTRelative? = null, 
    @SerialName("dy") val dy : NGTRelative? = null, 
    @SerialName("body") val body : NGTContent, 
) : NGTContent
{
    override fun convert() = TPlace(alignment?.convert().cast(), float?.convert().cast(), clearance?.convert().cast(), dx?.convert().cast(), dy?.convert().cast(), body?.convert().cast())
}
