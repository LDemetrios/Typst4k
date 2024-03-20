@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("grid.footer")
@Serializable
data class NGTFooter(
    @SerialName("repeat") val repeat : NGTBool? = null, 
    @SerialName("children") val children : NGTArray<TContent, >, 
) : NGTContent
{
    override fun convert() = TFooter(repeat?.convert().cast(), children?.convert().cast())
}
