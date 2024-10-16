package org.ldemetrios.typst4k.rt

import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import org.ldemetrios.js.*
import org.ldemetrios.typst4k.orm.TValue
import org.ldemetrios.utilities.cast
import org.ldemetrios.utilities.castOrNull

@OptIn(InternalSerializationApi::class)
class CustomMapSerializer<E : TValue>(key: KSerializer<String>, element: KSerializer<E>) :
    KSerializer<Map<String, E>> by MapSerializer(
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
    is JSString -> {
        JSObject.of(mapOf("type" to "str".js, "value" to it))
    }
    is JSObject -> {
        var res = it.filterKeys { it !in listOf("type", "func") }
            .mapValues { transform(it.value) }
            .toMutableMap()

        val type = it["type"]
            .run { if (this == JSUndefined) null else this }
            ?.cast<JSString>()
            ?.str
            ?.let { if (it == "relative") "relative-impl" else it }
        val func = it["func"]
            .run { if (this == JSUndefined) null else this }
            ?.let {
                it.castOrNull<JSString>() ?: throw SerializationException("Non-string type descriptor: $it")
            }?.str

        when ((type != null) to (func != null)) {
            true to true -> {
                res["type"] = when (type) {
                    "func" -> throw SerializationException("Dynamic functions aren't supported")
                    "gradient" -> func!!
                    "content" -> when(func!!){
                        "vline", "hline", "header", "footer", "cell" -> "grid.$func"
                        "caption" -> "figure.caption"
                        "flush" -> "place.flush"
                        else -> func
                    }
                    "selector" -> "$func-selector"
                    "counter-key" -> "$func-counter-key"

                    "color" -> when (func) {
                        "linear-rgb", "hsl", "hsv" -> "color.$func"
                        else -> func!!
                    }
                    "transform", "style", "property" -> type // TODO revisit after proper work on Typst side
                    else -> throw AssertionError("$type . $func")
                }.js
            }

//            true to false -> res["type"] = type!!.js
            true to false -> when (type) {
                "func" -> throw SerializationException("Dynamic functions aren't supported")
                else -> res["type"] = type!!.js
            }

            false to true -> {
//                throw AssertionError("null . $func")
                res["type"] = func!!.js
            }

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
    val raw = JSParser.parseValue(input)
    System.err.println(raw.toString(4))
    System.err.println()
    val js = transform(raw)
    System.err.println(js.toString(4))
    System.err.println()
    return json.decodeFromString<T>(js.toString())
}
