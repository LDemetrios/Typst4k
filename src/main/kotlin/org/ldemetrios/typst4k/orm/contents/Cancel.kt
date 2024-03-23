package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("cancel")
@Serializable
data class TCancel(
    @SerialName("body") val body : TContent, 
    @SerialName("length") val length : TRelative? = null, 
    @SerialName("inverted") val inverted : TBool? = null, 
    @SerialName("cross") val cross : TBool? = null, 
    @SerialName("angle") val angle : TAngleOrAuto? = null, 
    @SerialName("stroke") val stroke : TColorOrDictionaryOrGradientOrLengthOrPatternOrStroke<*, >? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("math.cancel", Triple(false, null, body), Triple(false, "length", length), Triple(false, "inverted", inverted), Triple(false, "cross", cross), Triple(false, "angle", angle), Triple(false, "stroke", stroke), )
}
