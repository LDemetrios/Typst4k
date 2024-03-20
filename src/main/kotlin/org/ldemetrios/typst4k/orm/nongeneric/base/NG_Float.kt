@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("float")
@Serializable
data class NGTFloat(val value : Double) : NGTValue, 
    NGTFloatOrRatio, 
    NGTAutoOrFloat {
    override fun convert() = org.ldemetrios.typst4k.orm.TFloat(value)
}
