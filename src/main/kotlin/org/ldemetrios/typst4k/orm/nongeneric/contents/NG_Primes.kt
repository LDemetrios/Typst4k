@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("math.primes")
@Serializable
data class NGTPrimes(
    @SerialName("count") val count : NGTInt, 
) : NGTContent
{
    override fun convert() = TPrimes(count?.convert().cast())
}
