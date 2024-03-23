package org.ldemetrios.typst4k.rt

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import kotlinx.serialization.serializer
import org.ldemetrios.js.*
import org.ldemetrios.typst4k.orm.TValue
import org.ldemetrios.utilities.cast

@OptIn(InternalSerializationApi::class)
class CustomMapSerializer<E : TValue>(key: KSerializer<String>, element: KSerializer<E>) : KSerializer<Map<String, E>> by MapSerializer(
    key,
    run {
//        println("Map: $element")
        if (element is PolymorphicSerializer) {
            element.baseClass.serializer()
        } else {
            element
        }
    }
)

@OptIn(InternalSerializationApi::class)
internal class CustomListSerializer<E : TValue>(element: KSerializer<E>) : KSerializer<List<E>> by ListSerializer(
    run {
//        println("List: $element")
        if (element is PolymorphicSerializer) {
            element.baseClass.serializer()
        } else {
            element
        }
    }
)


@PublishedApi
internal fun transform(it: JSStuff): JSStuff = when (it) {
    is JSString -> JSObject.of(mapOf("type" to "str".js, "value" to it))
    is JSObject -> {
        var res = it.filter { it.key !in listOf("type", "func") }.toList()
            .associateTo(mutableMapOf()) { it.first to transform(it.second) }

        val type = it["type"].run { if (this == JSUndefined) null else this }?.cast<JSString>()?.str
        val func = it["func"].run { if (this == JSUndefined) null else this }?.cast<JSString>()?.str

        when ((type != null) to (func != null)) {
            true to true -> {
//                if (type == "gradient") {
//                    res["type"] = "$type.$func".js
//                } else {
                    res["type"] = func!!.js
//                }
            }

            true to false -> res["type"] = type!!.js

            false to true -> res["type"] = func!!.js

            else -> res = mutableMapOf(
                "type" to "dictionary".js,
                "value" to JSObject.of(res)
            )
        }
        JSObject.of(res)
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
//    println(js.toString(4))
    return json.decodeFromString<T>(js.toString())/*.convert().cast()*/
}
