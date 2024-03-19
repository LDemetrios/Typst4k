package parsers

import kotlin.math.max

open class BaseParser protected constructor(val source: String) {
    private var pos = 0

    fun hasNext(): Boolean {
        return pos < source.length
    }

    fun next(): Char {
        return source[pos++]
    }

    fun error(message: String): IllegalArgumentException {
        return IllegalArgumentException("$pos: $message; " + this.toString().replace("\n", " "))
    }

    override fun toString(): String = source.substring(max(pos - 1, 0))

    init {
        take()
    }

    private var ch = source[0]
    private var last = 0xffff.toChar()

    protected fun take(): Char {
        last = ch
        ch = if (hasNext()) next() else END
        return last
    }

    protected fun test(expected: Char): Boolean {
        return ch == expected
    }

    protected fun test(expected: (Char) -> Boolean): Boolean {
        return expected(ch)
    }

    protected fun take(expected: Char): Boolean {
        if (test(expected)) {
            take()
            return true
        }
        return false
    }

    protected fun expect(expected: Char) {
        if (!take(expected)) {
            throw error("Expected '$expected', found '$ch'")
        }
    }

    protected open fun lookup(value: String): Boolean {
        var x = 0
        for (c in value) {
            if (take(c)) x++
            else {
                if (pos > 1) {
                    pos -= x + 2
                    take()
                    take()
                } else {
                    pos = 0
                    ch = source[0]
                    last = 0xffff.toChar()
                    take()
                }
                return false
            }
        }
        return true
    }

    protected fun expect(value: String) {
        for (c in value) {
            expect(c)
        }
    }

    protected fun eof(): Boolean {
        return take(END)
    }

    protected fun between(from: Char, to: Char): Boolean {
        return from <= ch && ch <= to
    }

    protected fun skipWhitespace(): Int {
        var skipped = 0
        while (!eof() && Character.isWhitespace(ch)) {
            skipped++
            take()
        }
        return skipped
    }

    protected fun cantResolve(additionalMessage: String?): String {
        val charsToGet = 10
        val sb: StringBuilder = StringBuilder("Can't resolve symbol (char " + pos + "): ->...")
        sb.append(tryTake(charsToGet))
        if (eof()) {
            sb.append("\$EOF")
        }
        if (additionalMessage != null) {
            sb.append(" (")
            sb.append(additionalMessage)
            sb.append(")")
        }
        return sb.toString()
    }

    protected fun tryTake(count: Int): String {
        val sb: StringBuilder = StringBuilder()
        var i = 0
        while (i < count && !eof()) {
            sb.append(take())
            i++
        }
        return sb.toString()
    }

    protected fun lastReturned(): Char {
        return last
    }

    protected fun peek(): Char {
        return ch
    }

    companion object {
        private const val END = '\u0000'
    }
}