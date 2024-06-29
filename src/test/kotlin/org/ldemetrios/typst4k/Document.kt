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
        TEquation(
            TSequence(
                "7.32".text, TSpace, "β".text, TSpace, "+".text, TSpace,
                TAttach(
                    "∑".text,
                    t = "∇".text,
                    b = TSequence("i".text, "=".text, "0".text)
                ),
                TSpace,
                TFrac(
                    TSequence(
                        TAttach("Q".text, b = "i".text),
                        TSpace,
                        TLr(
                            TSequence(
                                "(".text,
                                TAttach("a".text, b = "i".text),
                                TSpace,
                                "−".text, TSpace, "ε".text, ")".text,
                            )
                        ),
                    ),
                    "2".text
                )
            ),
            block = true.t
        )
    )
    File("example.typ").writeText("#set page(height:auto)\n #" + content.repr())
    Typst("./typst").compile("example.typ", "example.png")
    File("example.typ").delete()
}
