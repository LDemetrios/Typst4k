package org.ldemetrios.typst4k.orm

import org.ldemetrios.js.JSStuff
import org.ldemetrios.typst4k.Lines
import org.ldemetrios.typst4k.join
import org.ldemetrios.utilities.Either
import kotlin.reflect.KType

data class EitherDeserializer(val variants: List<Deserializer>, val name: String? = null) : Deserializer {
    constructor(vararg variants: Deserializer) : this(variants.toList())

    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        val err = mutableListOf<Lines?>()
        for (deserializer in variants) {
            if (deserializer.canDeserialize(type)) {
                val res = deserializer.deserializeAs(pool, json, type, what)
                if (res.isLeft()) err.add(res.getLeft())
                else return res
            } else err.add(null)
        }
        val errHeavy = Lines.of("$this failed deserializing" + if (what == null) "" else " $what")
        return Either.Left(
            errHeavy +
                    err.mapIndexed { index, it ->
                        it ?: Lines.of(variants[index].toString() + " is incapable of deserializing $type")
                    }.join().tab()
        )
    }

    override fun canDeserialize(type: KType): Boolean = variants.any { it.canDeserialize(type) }

    override fun toString(): String = name ?: "EitherDeser(${variants.joinToString { it.toString() }})"
}
