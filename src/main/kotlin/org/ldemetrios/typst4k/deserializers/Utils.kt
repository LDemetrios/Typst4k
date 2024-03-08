package org.ldemetrios.typst4k.deserializers

import org.ldemetrios.js.JSString
import org.ldemetrios.js.JSStuff
import org.ldemetrios.js.JSUndefined
import org.ldemetrios.typst4k.utils.Lines
import org.ldemetrios.utilities.Either
import org.ldemetrios.utilities.replaceAll


internal fun error(str: String) = Either.Left(Lines.of(str))

internal inline fun <reified T : JSStuff> JSStuff.assertIs(
    type: String,
    func: (T) -> Either<Lines, Any>
): Either<Lines, Any> {
    if (this !is T) {
        return error("'$type' should be represented by ${T::class.simpleName}")
    }
    try {
        return func(this)
    } catch (e: Exception) {
        e.printStackTrace()
        return error("Exception $e during parsing")
    }
}

internal fun JSStuff.assertString(
    type: String,
    regex: String,
    func: (String) -> Either<Lines, Any>
): Either<Lines, Any> {
    if (this !is JSString) {
        return error("'$type' should be represented by JSString")
    } else if (!regex.toRegex().matches(this.str)) {
        val regexToStr = regex.replaceAll(mapOf("\n" to "\\n", "\t" to "\\t", "\r" to "\\r"))
        return error("'$type' should be represented by string matching /$regexToStr/")
    }
    return func(this.str)
}

internal operator fun String?.div(other: String) = if (this == null) other else "$this/$other"

internal fun JSStuff.norm() = if (this == JSUndefined) null else this
