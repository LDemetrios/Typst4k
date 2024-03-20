package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("grid.hline")
@Serializable
data class THline(
    @SerialName("y") val y : TAutoOrInt? = null, 
    @SerialName("start") val start : TInt? = null, 
    @SerialName("end") val end : TIntOrNone? = null, 
    @SerialName("stroke") val stroke : TColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<*, >? = null, 
    @SerialName("position") val position : TAlignment? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("grid.hline", "y" to y, "start" to start, "end" to end, "stroke" to stroke, "position" to position, )
}
