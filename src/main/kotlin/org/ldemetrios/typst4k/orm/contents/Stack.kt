package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("stack")
@Serializable
data class TStack(
    @SerialName("dir") val dir : TDirection? = null, 
    @SerialName("spacing") val spacing : TFractionOrNoneOrRelative? = null, 
    @SerialName("children") val children : TArray<TContentOrFractionOrRelative, >? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("stack", Triple(false, "dir", dir), Triple(false, "spacing", spacing), Triple(false, "children", children), )
}
