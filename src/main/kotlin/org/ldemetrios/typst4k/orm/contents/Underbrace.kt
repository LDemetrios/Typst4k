package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("math.underbrace")
@Serializable
data class TUnderbrace(
    @SerialName("body") val body : TContent, 
    @SerialName("annotation") val annotation : TNoneOrNonecontent? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("math.underbrace", null to body, null to annotation, )
}
