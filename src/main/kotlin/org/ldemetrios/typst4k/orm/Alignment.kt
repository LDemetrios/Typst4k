package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("alignment")
@Serializable
data class TAlignment(
    @SerialName("x") val x : TStr? = null, 
    @SerialName("y") val y : TStr? = null, 
) : TValue, 
    TAlignmentOrAutoOrNone, 
    TAlignmentOrArrayOrAuto<Nothing>, 
    TAlignmentOrAuto
{
    override fun repr() : String = RT.reprOf(this)
}
