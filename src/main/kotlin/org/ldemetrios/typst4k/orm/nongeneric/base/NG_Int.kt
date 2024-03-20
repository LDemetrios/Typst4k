@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("int")
@Serializable
data class NGTInt(val value : Long) : NGTValue, 
    NGTIntOrRatio, 
    NGTAutoOrInt, 
    NGTIntOrLength, 
    NGTIntOrNone, 
    NGTArrayOrAutoOrFractionOrIntOrRelative<Nothing>, 
    NGTIntOrStr, 
    NGTDictionaryOrIntOrNone<Nothing> {
    override fun convert() = org.ldemetrios.typst4k.orm.TInt(value)
}
