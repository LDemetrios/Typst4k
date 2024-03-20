@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("angle")
@Serializable
data class NGTAngle(
    @SerialName("deg") val deg : NGTFloat, 
) : NGTValue, 
    NGTAngleOrAuto
{
    override fun convert() = TAngle(deg?.convert().cast())
}
