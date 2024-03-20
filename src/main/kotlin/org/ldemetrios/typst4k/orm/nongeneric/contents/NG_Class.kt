@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("math.class")
@Serializable
data class NGTClass(
    @SerialName("class") val clazz : NGTStr, 
    @SerialName("body") val body : NGTContent, 
) : NGTContent
{
    override fun convert() = TClass(clazz?.convert().cast(), body?.convert().cast())
}
