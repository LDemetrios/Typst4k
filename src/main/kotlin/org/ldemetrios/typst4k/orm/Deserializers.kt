package org.ldemetrios.typst4k.orm

import org.ldemetrios.js.*
import org.ldemetrios.typst4k.Lines
import org.ldemetrios.utilities.Either
import kotlin.reflect.KType
import kotlin.reflect.typeOf

class DeserializationException(message: String) : IllegalArgumentException(message)

interface Deserializer {
    fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any>
    fun canDeserialize(type: KType): Boolean
}

enum class OnMulti {
    Succeeded, Any
}

enum class NotifyOnMulti {
    Warning, Error, Nothing
}

class DeserializerPool {
    private val deserializers = mutableListOf<Deserializer>()

    fun register(deserializer: Deserializer) {
        deserializers.add(deserializer)
    }

    inline fun <reified T> deserialize(json: JSStuff): T {
        val either = deserializeAs(json, typeOf<T>())
        if (either.isLeft()) {
            throw DeserializationException("Deserialization failed:\n${either.getLeft()}")
        } else {
            return either.getRight() as T
        }
    }

    @PublishedApi
    internal fun deserializeAs(json: JSStuff, type: KType, what: String? = ""): Either<Lines, Any> {
        val suitable = deserializers.filter { it.canDeserialize(type) }
        if (suitable.isEmpty()) return Either.Left(
            Lines.of("No deserializer found for type $type ${if (what == null) "" else "declared for $what"}")
        )

        var errMessage = Lines.of()
        for (deserializer in suitable) {
            val res = deserializer.deserializeAs(this, json, type, what)
            if (res.isLeft()) {
                errMessage += "$deserializer [suitable for deserialization of $type] failed:"
                errMessage += res.getLeft().tab()
            } else return res
        }
        errMessage += "No more deserializers found for $type"
        return Either.Left(errMessage)
    }
}


