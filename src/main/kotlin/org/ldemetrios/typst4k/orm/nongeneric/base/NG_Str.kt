@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("str")
@Serializable
data class NGTStr(val value : String) : NGTValue, 
    NGTAutoOrStr, 
    NGTArrayOrStr<Nothing>, 
    NGTNoneOrStr, 
    NGTLengthOrStr, 
    NGTDictionaryOrLabelOrLocationOrStr<Nothing>, 
    NGTArrayOrAutoOrDictionaryOrStr<Nothing, Nothing>, 
    NGTIntOrStr, 
    NGTContentOrStr, 
    NGTArrayOrAutoOrDictionaryOrNoneOrStr<Nothing, Nothing> {
    override fun convert() = org.ldemetrios.typst4k.orm.TStr(value)
}
