package org.ldemetrios.typst4k.rt

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import org.ldemetrios.js.*
import org.ldemetrios.typst4k.orm.TValue
import org.ldemetrios.typst4k.orm.nongeneric.NGTValue
import org.ldemetrios.utilities.cast

@PublishedApi
internal fun transform(it: JSStuff): JSStuff = when (it) {
    is JSString -> JSObject.of(mapOf("type" to "str".js, "value" to it))
    is JSObject -> when {
        "type" in it && "func" !in it -> JSObject.of(
            mapOf("type" to it["type"]) +
                    it.filter { it.key !in listOf("type") }.toList()
                        .associate { it.first to transform(it.second) }
        )

        "func" in it -> JSObject.of(
            mapOf("type" to it["func"]) +
                    it.filter { it.key !in listOf("type", "func") }.toList()
                        .associate { it.first to transform(it.second) }
        )

        else -> JSObject.of(
            mapOf(
                "type" to "dictionary".js,
                "value" to JSObject.of(it.mapValues { transform(it.value) })
            )
        )
    }

    is JSUndefined -> throw AssertionError()

    is JSNull -> JSObject.of(mapOf("type" to "none".js))
    is JSNumber -> if (it.toString().contains(".")) {
        JSObject.of(mapOf("type" to "float".js, "value" to it))
    } else {
        JSObject.of(mapOf("type" to "int".js, "value" to it))
    }

    is JSArray -> JSObject.of(mapOf("type" to "array".js, "value" to JSArray.of(it.map { transform(it) })))
    is JSBoolean -> JSObject.of(mapOf("type" to "bool".js, "value" to it))
    else -> throw AssertionError()
}

@PublishedApi
internal val json = Json {
    ignoreUnknownKeys = true
    encodeDefaults = false
    serializersModule = SerializersModule {
        contextual(TValue.serializer())
    }
}

inline fun <reified T : TValue> deserializeTypstValue(input: String): T {
    val js = transform(JSParser.parseValue(input))
    return json.decodeFromString<NGTValue>(js.toString()).convert().cast()
}
