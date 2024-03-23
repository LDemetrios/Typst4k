package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("cite")
@Serializable
data class TCite(
    @SerialName("key") val key : TLabel, 
    @SerialName("supplement") val supplement : TContentOrNone? = null, 
    @SerialName("form") val form : TNoneOrStr? = null, 
    @SerialName("style") val style : TAutoOrStr? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("cite", Triple(false, null, key), Triple(false, "supplement", supplement), Triple(false, "form", form), Triple(false, "style", style), )
}
