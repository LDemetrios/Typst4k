package org.ldemetrios.typst4k.utils


data class Lines internal constructor(val lines: List<Pair<String, Int>>) {
    companion object {
        fun of(lines: List<String>) =
            Lines(
                lines.flatMap {
                    it.split(Regex("\r\n|[\r\n\u2028\u2029\u0085]")).map { it to 0 }
                }
            )

        fun of(line: String) = Lines(listOf(line to 0))
        fun of() = Lines(listOf())
    }

    fun toList() = lines.map { "|   ".repeat(it.second) + it.first }

    override fun toString() = toList().joinToString("\n")

    operator fun plus(other: Lines) = Lines(lines + other.lines)
    operator fun plus(other: String) = this + of(other)

    fun tab() = indented(1);

    fun indented(indent: Int) = Lines(lines.map { it.copy(second = it.second + indent) })

    fun deindented(indent: Int) = Lines(lines.map { it.copy(second = maxOf(it.second - 1, 0)) })

    fun detab() = deindented(1)
    operator fun plus(pack: List<Lines>): Lines = this + pack.join()
}

fun List<Lines>.join() = Lines(flatMap { it.lines })