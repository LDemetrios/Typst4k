@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("stack")
@Serializable
data class NGTStack(
    @SerialName("dir") val dir : NGTDirection? = null, 
    @SerialName("spacing") val spacing : NGTFractionOrNoneOrRelative? = null, 
    @SerialName("children") val children : NGTArray<TContentOrFractionOrRelative, >? = null, 
) : NGTContent
{
    override fun convert() = TStack(dir?.convert().cast(), spacing?.convert().cast(), children?.convert().cast())
}
