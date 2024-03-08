package org.ldemetrios.typst4k.deserializers

import org.json.JSONArray
import org.json.JSONObject
import org.ldemetrios.js.*
import org.ldemetrios.typst4k.utils.Lines
import org.ldemetrios.utilities.Either
import org.ldemetrios.utilities.cast
import kotlin.reflect.KClassifier
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
    @PublishedApi
    internal val deserializers = mutableMapOf<KClassifier, Deserializer>()

    inline fun <reified T> register(deserializer: Deserializer) {
        deserializers[typeOf<T>().classifier!!] = deserializer
    }

    inline fun <reified T> deserialize(json: JSStuff): T = deserialize0(json, typeOf<T>()) as T

    @PublishedApi
    internal fun deserialize0(json: JSStuff, type: KType) : Any{
        val either = deserializeAs(json, type)
        if (either.isLeft()) {
            throw DeserializationException(
                "Deserialization failed:\n${
                    either.getLeft().toString()
                        .replace("org.ldemetrios.typst4k.deserializers.", "")
                        .replace("org.ldemetrios.typst4k.orm.", "")
                }"
            )
        } else {
            return either.getRight()
        }
    }

    @PublishedApi
    internal fun deserializeAs(json: JSStuff, type: KType, what: String? = ""): Either<Lines, Any> {
        val suitable = deserializers[type.classifier!!]
        if (suitable != null) {
            try {
                return suitable.deserializeAs(this, json, type, what)
            } catch (e: Exception) {
                e.printStackTrace()
                return error("Exception $e during parsing")
            }
        } else {
            return Either.Left(Lines.of("No one deserializer is suitable for $type"))
        }
    }

    @PublishedApi
    internal inline fun <reified T> deserializeAs(json: JSStuff, what: String? = "") =
        deserializeAs(json, typeOf<T>(), what).mapRight { it.cast<T>() }
}


fun main() {
    val string = ""
    val json = JSONArray(string)
    println(json[0].cast<JSONObject>().get("value").cast<JSONObject>().get("floats").cast<JSONArray>().get(2).javaClass)
}

