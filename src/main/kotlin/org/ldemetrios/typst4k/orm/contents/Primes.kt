package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("math.primes")
@Serializable
data class TPrimes(
    @SerialName("count") val count : TInt, 
) : TContent
{
    override fun repr() : String = RT.structRepr("math.primes", null to count, )
}
