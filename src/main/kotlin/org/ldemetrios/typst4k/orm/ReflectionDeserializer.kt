package org.ldemetrios.typst4k.orm

import org.ldemetrios.js.JSStuff
import org.ldemetrios.typst4k.Lines
import org.ldemetrios.utilities.Either
import kotlin.reflect.KType
import kotlin.reflect.typeOf

inline fun <reified T> ReflectionDeserializer() = ReflectionDeserializer<T>(typeOf<T>())

class ReflectionDeserializer<T>(clazz: KType) : Deserializer {
    val classifier = clazz.classifier ?: throw IllegalArgumentException("Only regular final classes are supported")

    init {
        classifier
    }

    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        TODO("Not yet implemented")
    }

    override fun canDeserialize(type: KType): Boolean {
        TODO("Not yet implemented")
    }
}

fun main(){
    println(typeOf<List<String>>())
}