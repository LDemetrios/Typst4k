package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("grid.vline")
@Serializable
data class TVline(
    @SerialName("x") val x : TAutoOrInt? = null, 
    @SerialName("start") val start : TInt? = null, 
    @SerialName("end") val end : TIntOrNone? = null, 
    @SerialName("stroke") val stroke : TColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<*, >? = null, 
    @SerialName("position") val position : TAlignment? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("grid.vline", Triple(false, "x", x), Triple(false, "start", start), Triple(false, "end", end), Triple(false, "stroke", stroke), Triple(false, "position", position), )
}
