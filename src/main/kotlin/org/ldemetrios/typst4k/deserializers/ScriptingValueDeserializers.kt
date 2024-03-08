package org.ldemetrios.typst4k.deserializers

import org.ldemetrios.js.*
import org.ldemetrios.js.JSStuff
import org.ldemetrios.typst4k.utils.Lines
import org.ldemetrios.typst4k.utils.join
import org.ldemetrios.typst4k.orm.*
import org.ldemetrios.utilities.Either
import org.ldemetrios.utilities.replaceAll
import kotlin.reflect.KType
import kotlin.reflect.typeOf

abstract class ScriptingValueDeserializer(val structType: KType) : Deserializer {
    override fun canDeserialize(type: KType): Boolean = type.classifier == structType.classifier
}

object NoneDeserializer : ScriptingValueDeserializer(typeOf<TNone>()) {
    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        return json.assertIs<JSNull>("none") {
            Either.Right(TNone)
        }
    }
}

object AutoDeserializer : ScriptingValueDeserializer(typeOf<TNone>()) {
    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        return json.assertString("auto", "auto") {
            if (it == "auto") {
                Either.Right(TAuto)
            } else {
                error("'auto' should be represented by \"auto\" string")
            }
        }
    }
}

object AngleDeserializer : ScriptingValueDeserializer(typeOf<TAngle>()) {
    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        return json.assertString("angle", "-?[0-9]*(\\.[0-9]*)?deg") {
            Either.Right(TAngle(it.dropLast(3).toDouble()))
        }
    }
}

object ArrayDeserializer : ScriptingValueDeserializer(typeOf<TArray<TypstValue>>()) {
    private fun KType.arrayValueType(): KType  = when(classifier){
        typeOf<TArray<*>>().classifier -> arguments[0].type
        typeOf<TArrayOrDictionary<*, *>>().classifier -> arguments[0].type
        typeOf<TypstValue>().classifier -> typeOf<TypstValue>()
        typeOf<TStrOrArray<*>>().classifier -> arguments[0].type
        typeOf<TContentOrArray<*>>().classifier -> arguments[0].type
        typeOf<TAutoOrArray<*>>().classifier -> arguments[0].type
        else -> throw AssertionError("Could determine array value type for type $this")
    } ?: typeOf<TypstValue>()

    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        return json.assertIs<JSArray>("array") { json ->
            val items = json.mapIndexed { ind, item ->
                pool.deserializeAs(item, type.arrayValueType(), what / ind.toString())
            }
            if (items.all(Either<Lines, Any>::isRight)) {
                Either.Right(TArray(items.map { it.getRight() as TypstValue }))
            } else {
                Either.Left(
                    Lines.of("Could not deserialize $what:") +
                            items.mapNotNull(Either<Lines, Any>::getLeftOrNull).join().tab()
                )
            }
        }
    }
}

object BoolDeserializer : ScriptingValueDeserializer(typeOf<TBool>()) {
    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        return json.assertIs<JSBoolean>("bool") {
            Either.Right(TBool(it.toBoolean()))
        }
    }
}

object DatetimeDeserializer : ScriptingValueDeserializer(typeOf<TDatetime>()) {
    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        return json.assertString("datetime", "datetime\\(([a-z]*: [0-9]*(, )?)*\\)") {
            val args = it
                .substring(9, it.length - 1)
                .split(",")
                .associate { arg ->
                    val (name, value) = arg.split(":")
                    name.trim() to value.trim().toLong()
                }
            Either.Right(
                TDatetime(
                    year = TInt(args["year"]!!),
                    month = TInt(args["month"]!!),
                    day = TInt(args["day"]!!),
                    hour = args["hour"]?.let(::TInt),
                    minute = args["minute"]?.let(::TInt),
                    second = args["second"]?.let(::TInt),
                )
            )
        }
    }
}

object DictionaryDeserializer : ScriptingValueDeserializer(typeOf<TDictionary<TypstValue>>()) {
    private fun KType.dictionaryValueType(): KType  = when(classifier){
        typeOf<TDictionary<*>>().classifier -> arguments[0].type
        typeOf<TArrayOrDictionary<*, *>>().classifier -> arguments[1].type
        typeOf<TypstValue>().classifier -> typeOf< TypstValue>()
        else -> throw AssertionError("Could determine dictionary value type for type $this")
    }?: typeOf<TypstValue>()

    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        return json.assertIs<JSObject>("dictionary") { json ->
            val items = json.mapValues { (key, value) ->
                pool.deserializeAs(value, type.dictionaryValueType(), what / key)
            }
            if (items.values.all(Either<Lines, Any>::isRight)) {
                Either.Right(TDictionary(items.mapValues { it.value.getRight() as TypstValue }))
            } else {
                Either.Left(
                    Lines.of("Could not deserialize $what:") +
                            items.values.mapNotNull(Either<Lines, Any>::getLeftOrNull).join().tab()
                )
            }
        }
    }
}

object DurationDeserializer : ScriptingValueDeserializer(typeOf<TDuration>()) {
    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        return json.assertString("duration", "duration\\(([a-z]*: [0-9]*(, )?)*\\)") {
            val args = it
                .substring(9, it.length - 1)
                .split(",")
                .associate { arg ->
                    val (name, value) = arg.split(":")
                    name.trim() to value.trim().toLong()
                }
            Either.Right(
                TDuration(
                    weeks = args["weeks"]?.let(::TInt),
                    days = args["days"]?.let(::TInt),
                    hours = args["hours"]?.let(::TInt),
                    minutes = args["minutes"]?.let(::TInt),
                    seconds = args["seconds"]?.let(::TInt),
                )
            )
        }
    }
}

object FloatDeserializer : ScriptingValueDeserializer(typeOf<TFloat>()) {
    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        return json.assertIs<JSNumber>("float") {
            Either.Right(TFloat(it.toDouble()))
        }
    }
}

object FractionDeserializer : ScriptingValueDeserializer(typeOf<TFraction>()) {
    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        return json.assertString("fraction", "-?[0-9]*(.[0-9])?fr") {
            Either.Right(TFraction(it.dropLast(2).toDouble()))
        }
    }
}

object IntDeserializer : ScriptingValueDeserializer(typeOf<TInt>()) {
    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        return json.assertIs<JSNumber>("int") {
            Either.Right(TInt(it.toLong()))
        }
    }
}

object LabelDeserializer : ScriptingValueDeserializer(typeOf<TLabel>()) {
    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        return json.assertString("label", "<.*>") {
            Either.Right(TLabel(TStr(it.substring(1, it.length - 1))))
        }
    }
}

object RegexDeserializer : ScriptingValueDeserializer(typeOf<TRegex>()) {
    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        return json.assertString("regex", "regex\\(\".*\"\\)") {
            Either.Right(
                TRegex(
                    TStr(
                        it.substring(8, it.length - 2)
                            .replaceAll(
                                mapOf(
                                    "\\\\" to "\\",
                                    "\\r" to "\r",
                                    "\\n" to "\n",
                                    "\\t" to "\t",
                                    "\\b" to "\b",
                                )
                            )
                    )
                )
            )
        }
    }
}

object StrDeserializer : ScriptingValueDeserializer(typeOf<TStr>()) {
    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        return json.assertString("str", ".*") {
            Either.Right(TStr(it))
        }
    }
}

object VersionDeserializer : ScriptingValueDeserializer(typeOf<TVersion>()) {
    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        return json.assertString("version", "version\\((-?[0-9]*(, )?)*\\)") {
            val args = it
                .substring(9, it.length - 1)
                .split(",")
                .filter { it.isNotBlank() }
                .map { it.trim().toLong() }
            Either.Right(TVersion(args.map(::TInt)))
        }
    }
}

object LengthDeserializer : ScriptingValueDeserializer(typeOf<TLength>()) {
    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        return json.assertString("length", "(-?[0-9]*(\\.[0-9]*)?(pt|em)( \\+ )?)*") {
            val args = it
                .split("+")
                .map(String::trim)
                .associate {
                    val unit = it.takeLast(2)
                    val value = it.dropLast(2).toDouble()
                    unit to value
                }

            Either.Right(
                TLength(
                    TFloat(args["pt"] ?: .0),
                    TFloat(args["em"] ?: .0)
                )
            )
        }
    }
}

object RatioDeserializer : ScriptingValueDeserializer(typeOf<TRatio>()) {
    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        return json.assertString("ratio", "-?[0-9]*(.[0-9])?%") {
            Either.Right(TRatio(TFloat(it.dropLast(1).toDouble())))
        }
    }
}

object RelativeDeserializer : ScriptingValueDeserializer(typeOf<TRelative>()) {
    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        return json.assertString("relative", "(-?[0-9]*(\\.[0-9]*)?(pt|em|%)( \\+ )?)*") {
            val args = it
                .split("+")
                .map(String::trim)
                .associate {
                    val unit = if (it.last() == '%') "%" else it.takeLast(2)
                    val value = it.dropLast(unit.length).toDouble()
                    unit to value
                }
            Either.Right(
                TLength(
                    TFloat(args["pt"] ?: .0),
                    TFloat(args["em"] ?: .0),
                ) + TRatio(
                    TFloat(args["%"] ?: .0)
                )
            )
        }
    }
}

object DirectionDeserializer : ScriptingValueDeserializer(typeOf<TDirection>()) {
    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        return json.assertString("direction", "ltr|rtl|ttb|btt") {
            Either.Right(TDirection.valueOf(it))
        }
    }
}

object AlignmentDeserializer : ScriptingValueDeserializer(typeOf<TAlignment>()) {
    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        return json.assertString("alignment", "((start|left|right|center|end|top|horizon|bottom)( \\+ )?)*") {
            val args = it
                .split("+")
                .map(String::trim)
                .map(TAlignment::valueOf)
                .reduce(TAlignment::plus)
            Either.Right(args)
        }
    }
}

object ColorDeserializer : ScriptingValueDeserializer(typeOf<TColor>()) {
    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        return json.assertString("color", "(rgb|oklab|oklch|color.(linear-rgb|hsl|hsv)|rgb|cmyk)\\(.*\\)") {
            val func = it.substring(0, it.indexOf('('))
            val args = it
                .substring(it.indexOf('(') + 1, it.length - 1)
                .split(',')
                .map(::JSString)

            var error: Lines? = null
            fun <T> Either<Lines, T>.assertOK() = if (isLeft()) {
                error = this.getLeft()
                throw AssertionError()
            } else this.getRight()

            try {
                when (func) {
                    "rgb" -> {
                        TColorRGB(
                            pool.deserializeAs<TStr>(args[0], what / "rgb.hex").assertOK()
                        )
                    }

                    "oklab" -> {
                        TColorOklab(
                            pool.deserializeAs<TRatio>(args[0], what / "oklab.lightness").assertOK(),
                            pool.deserializeAs<TFloatOrRatio>(args[1], what / "oklab.a").assertOK(),
                            pool.deserializeAs<TFloatOrRatio>(args[2], what / "oklab.b").assertOK(),
                            args.getOrNull(3)?.let { pool.deserializeAs<TRatio>(it, what / "oklab.alpha")}?.assertOK()
                        )
                    }

                    "oklch" -> {
                        TColorOklch(
                            pool.deserializeAs<TRatio>(args[0],what / "oklch.lightness").assertOK(),
                            pool.deserializeAs<TFloatOrRatio>(args[1],what / "oklch.chroma").assertOK(),
                            pool.deserializeAs<TAngle>(args[2],what / "oklch.hue").assertOK(),
                            args.getOrNull(3)?.let { pool.deserializeAs<TRatio>(it,what / "oklch.alpha")}?.assertOK()
                        )
                    }

                    "color.linear-rgb" -> {
                        TColorLinearRGB(
                            pool.deserializeAs<TIntOrRatio>(args[0],what / "color.linear-rgb.red").assertOK(),
                            pool.deserializeAs<TIntOrRatio>(args[1],what / "color.linear-rgb.green").assertOK(),
                            pool.deserializeAs<TIntOrRatio>(args[2],what / "color.linear-rgb.blue").assertOK(),
                            args.getOrNull(3)?.let { pool.deserializeAs<TIntOrRatio>(it,what / "color.linear-rgb.alpha")}?.assertOK()
                        )
                    }
                    "color.hsl" -> {
                        TColorHSL(
                            pool.deserializeAs<TAngle>(args[0],what / "color.hsl.hue").assertOK(),
                            pool.deserializeAs<TRatio>(args[1],what / "color.hsl.saturation").assertOK(),
                            pool.deserializeAs<TRatio>(args[2],what / "color.hsl.lightness").assertOK(),
                            args.getOrNull(3)?.let { pool.deserializeAs<TRatio>(it,what / "color.hsl.alpha")}?.assertOK()
                        )
                    }

                    "color.hsv" -> {
                        TColorHSV(
                            pool.deserializeAs<TAngle>(args[0],what / "color.hsv.hue").assertOK(),
                            pool.deserializeAs<TIntOrRatio>(args[1],what / "color.hsv.saturation").assertOK(),
                            pool.deserializeAs<TIntOrRatio>(args[2],what / "color.hsv.value").assertOK(),
                            args.getOrNull(3)?.let { pool.deserializeAs<TIntOrRatio>(it,what / "color.hsv.alpha")}?.assertOK()
                        )
                    }

                    "cmyk" -> {
                        TColorCMYK(
                            pool.deserializeAs<TRatio>(args[0],what / "cmyk.cyan").assertOK(),
                            pool.deserializeAs<TRatio>(args[1],what / "cmyk.magenta").assertOK(),
                            pool.deserializeAs<TRatio>(args[2],what / "cmyk.yellow").assertOK(),
                            pool.deserializeAs<TRatio>(args[3],what / "cmyk.black").assertOK(),
                            args.getOrNull(4)?.let { pool.deserializeAs<TRatio>(it,what / "cmyk.alpha")}?.assertOK()
                        )
                    }

                    "luma" -> {
                        TColorLuma(
                            pool.deserializeAs<TIntOrRatio>(args[0],what / "luma.lightness").assertOK()
                        )
                    }

                    else -> error("Illegal function $func for color").assertOK()
                }.let { Either.Right(it) }
            } catch (e: AssertionError) {
                Either.Left(
                    Lines.of("Could not deserialize color $what:") +
                            error!!.tab()
                )
            }
        }
    }
}
