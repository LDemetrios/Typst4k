@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("arguments")
@Serializable
data class NGTArguments<out A : TValue>(
    @SerialName("positional") val positional : NGTArray<A, >, 
    @SerialName("named") val named : NGTDictionary<A, >, 
) : NGTValue
{
    override fun convert() = RT.convertArguments<A>(this)
}
