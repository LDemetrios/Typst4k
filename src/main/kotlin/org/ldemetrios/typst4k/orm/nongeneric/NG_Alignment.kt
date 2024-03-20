@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("alignment")
@Serializable
data class NGTAlignment(
    @SerialName("x") val x : NGTStr? = null, 
    @SerialName("y") val y : NGTStr? = null, 
) : NGTValue, 
    NGTAlignmentOrAutoOrNone, 
    NGTAlignmentOrArrayOrAuto<Nothing>, 
    NGTAlignmentOrAuto
{
    override fun convert() = TAlignment(x?.convert().cast(), y?.convert().cast())
}
