package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("math.overbrace")
@Serializable
data class TOverbrace(
    @SerialName("body") val body : TContent, 
    @SerialName("annotation") val annotation : TNoneOrNonecontent? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("math.overbrace", null to body, null to annotation, )
}
