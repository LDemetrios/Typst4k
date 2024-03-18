package org.ldemetrios.typst4k.selectors

import org.ldemetrios.typst4k.orm.*

fun formatWhere(base: String, args: Map<String, String?>): String {
    val args = args.filter { it.value != null }.mapValues { it.value!! }
    return if (args.isNotEmpty()) {
        base + ".where(" + args.map { "${it.key}: ${it.value}" }.joinToString(", ") + ")"
    } else base
}

fun <T : Any> intersect(x: T?, y: T?, what: String) =
    if (x != null && y != null) throw IllegalArgumentException("Conflicting restrictions for $what : $x and $y")
    else x ?: y

data class HeadingSelector(
    val level: TInt? = null,
    val numbering: TNoneOrStr? = null, /*or function*/
    val outlined: TBool? = null,
    val bookmarked: TAutoOrBool? = null,
) : Selector<THeading> {
    override fun toString(): String {
        return formatWhere(
            "heading",
            mapOf(
                "level" to level?.toString(),
                "numbering" to numbering?.toString(),
                "outlined" to outlined?.toString(),
                "bookmarked" to bookmarked?.toString(),
            )
        )
    }

    fun where(
        level: TInt? = null,
        numbering: TNoneOrStr? = null,
        outlined: TBool? = null,
        bookmarked: TAutoOrBool? = null,
    ) = HeadingSelector(
        intersect(level, this.level, "level"),
        intersect(numbering, this.numbering, "numbering"),
        intersect(outlined, this.outlined, "outlined"),
        intersect(bookmarked, this.bookmarked, "bookmarked"),
    )
}

val heading = HeadingSelector()


