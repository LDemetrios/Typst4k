@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("bool")
@Serializable
data class NGTBool(val value : Boolean) : NGTValue, 
    NGTAutoOrBool, 
    NGTAutoOrBoolOrNoneOrRelative {
    override fun convert() = org.ldemetrios.typst4k.orm.TBool(value)
}
