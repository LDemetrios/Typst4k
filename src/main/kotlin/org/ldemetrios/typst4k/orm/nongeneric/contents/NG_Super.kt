@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("super")
@Serializable
data class NGTSuper(
    @SerialName("typographic") val typographic : NGTBool? = null, 
    @SerialName("baseline") val baseline : NGTLength? = null, 
    @SerialName("size") val size : NGTLength? = null, 
    @SerialName("body") val body : NGTContent, 
) : NGTContent
{
    override fun convert() = TSuper(typographic?.convert().cast(), baseline?.convert().cast(), size?.convert().cast(), body?.convert().cast())
}
