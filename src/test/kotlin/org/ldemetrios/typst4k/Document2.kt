package org.ldemetrios.typst4k

import org.ldemetrios.typst4k.orm.*
import org.ldemetrios.typst4k.rt.*
import java.io.File

fun main() {
    val content = TSequence(
        THeading(body = "Methods".text, depth = 1.t),
        TSpace,
        "We follow the glacier melting models established earlier.".text,
        TParbreak,
        TCustomContent("lorem", listOf(15.t), mapOf()),
        TParbreak,
        "Total displaced soil by glacial flow:".text,
        TParbreak,
        TCustomContent(
            "eval",
            listOf("\$ 7.32 beta + sum_(i=0)^nabla (Q_i (a_i - epsilon)) / 2 \$".t),
            mapOf("mode" to "markup".t)
        )
    )
    File("example.typ").writeText("#set page(height:auto)\n #" + content.repr())
    Typst("./typst").compile("example.typ", "example.png")
    File("example.typ").delete()
}
