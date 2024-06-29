package org.ldemetrios.typst4k

import org.ldemetrios.typst4k.orm.*
import org.ldemetrios.typst4k.rt.*

fun main() {
    println(
        TPattern(
            size = TArray(30.pt, 30.pt),
            body = TSequence(
                TPlace(body = TLine(start = TArray(0.pc, 0.pc), end = TArray(100.pc, 100.pc))),
                TPlace(body = TLine(start = TArray(0.pc, 100.pc), end = TArray(100.pc, 0.pc))),
            )
        ).repr()
    )

    val typst = Typst("./typst")

    val x = typst.query<TValue>(
        "src/test/resources/org/ldemetrios/typst4k/test.typ",
        "full",
    ).orElseThrow().value.single()
    println(x.repr())
}
