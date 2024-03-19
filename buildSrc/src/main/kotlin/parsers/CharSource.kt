package parsers

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
interface CharSource {
    fun hasNext(): Boolean

    fun next(): Char

    fun error(message: String): IllegalArgumentException
}