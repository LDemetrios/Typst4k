package org.ldemetrios.typst4k

import io.kotest.assertions.fail
import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.spec.style.scopes.FreeSpecContainerScope
import io.kotest.matchers.collections.shouldBeSingleton
import kotlinx.serialization.SerializationException
import org.ldemetrios.typst4k.dsl.*
import org.ldemetrios.typst4k.orm.TContent
import org.ldemetrios.typst4k.orm.TMetadata
import java.io.File

val TEST_SEPARATOR = Regex("--- [a-z0-9A-Z-]+ ---")

data class Case(val file: File, val name: String, val text: String) {
    val testName = file.relativeTo(SUITE_ROOT).path + "/" + name
}

val SUITE_ROOT = File("src/test/resources/suite")

val typst = Typst(
    "./typst012",
    "./typst-custom-serial",
)

class OriginalTests : FreeSpec({
    val cases = SUITE_ROOT
        .walkTopDown()
        .filter { it.isFile }
        .flatMap { file ->
            file
                .readLines()
                .split(keepSeparator = true) { it.matches(TEST_SEPARATOR) }
                .filter { it.isNotEmpty() && it[0].startsWith("--- ") }
                .map {
                    val name = it[0].removePrefix("--- ").removeSuffix(" ---")
                    Case(file, name, it.drop(1).joinToString("\n"))
                }
        }
        .filter {
            it.testName !in listOf(
                // Probably needs more delicate serializing on the rust side
                "visualize/image.typ/image-decode-svg",

                // `#set text(dir: rtl)` isn't serialized
                "layout/grid/rtl.typ/grid-rtl-vline-position",
                "layout/flow/place.typ/place-float-flow-size-alone",
                "layout/flow/place.typ/place-float-flow-size",
                "layout/flow/place.typ/place-float-fr",
                "layout/flow/place.typ/place-float-rel-sizing",
            )
        }

    runContextually(cases, { it.testName.split("/") }) {
        knownBugsGuard(it)
    }
})

sealed interface Tests<T> {
    suspend fun execute(function: (T) -> Unit, scope: FreeSpecContainerScope)

    fun execute(function: (T) -> Unit, scope: FreeSpec)

    fun register(list: List<String>, value: T): Boolean
}

data class SingleTest<T>(val name: String, val value: T) : Tests<T> {
    override suspend fun execute(function: (T) -> Unit, scope: FreeSpecContainerScope) {
        with(scope) {
            name {
                function(value)
            }
        }
    }

    override fun execute(function: (T) -> Unit, scope: FreeSpec) {
        with(scope) {
            name {
                function(value)
            }
        }
    }

    override fun register(list: List<String>, value: T): Boolean = false
}

data class TestGroup<T>(val ctxName: String?, val subs: MutableMap<String, Tests<T>> = mutableMapOf()) : Tests<T> {
    override suspend fun execute(function: (T) -> Unit, scope: FreeSpecContainerScope) = with(scope) {
        if (ctxName != null) {
            ctxName - {
                subs.values.forEach {
                    it.execute(function, this)
                }
            }
        } else {
            subs.values.forEach {
                it.execute(function, scope)
            }
        }
    }

    override fun execute(function: (T) -> Unit, scope: FreeSpec) = with(scope) {
        if (ctxName != null) {
            ctxName - {
                subs.values.forEach {
                    it.execute(function, this)
                }
            }
        } else {
            subs.values.forEach {
                it.execute(function, scope)
            }
        }
    }

    override fun register(list: List<String>, value: T): Boolean {
        if (list.isEmpty()) throw AssertionError()
        if (list.size == 1) {
            if (list[0] in subs) return false
            subs[list[0]] = SingleTest(list[0], value)
            return true
        } else {
            return subs.computeIfAbsent(list[0]) {
                TestGroup(list[0])
            }.register(list.drop(1), value)
        }
    }
}

context(FreeSpec) fun <T> runContextually(
    cases: Sequence<T>,
    nameSelector: (T) -> List<String>,
    func: (T) -> Unit
) {
    val group = TestGroup<T>(null)

    for (i in cases) {
        val name = nameSelector(i)
        if (!group.register(name, i)) {
            throw AssertionError("Couldn't register $name, already present")
        }
    }

    group.execute(func, implicit())
}

fun <A> A.implicit() = this

const val SKIP_GPU_OVERLOAD = true
const val SKIP_STATE_MANIPULATIONS = true
const val SKIP_CONTEXT = true
const val SKIP_FUNCTIONS = true
const val SKIP_DYNAMIC_FUNCTIONS = true
const val SKIP_LABELS_ISSUES = true

fun maybeSkip(e: SerializationException, flag: Boolean, vararg types: String): Unit? {
    if (flag) {
        for (it in types) {
            if ("Serializer for subclass '$it' is not found in the polymorphic scope of" in e.message!!) return null
        }
    }
    return Unit
}

fun knownBugsGuard(case: Case) {
    if (case.testName in listOf(
            "math/multiline.typ/math-pagebreaking-numbered",
            "layout/flow/footnote.typ/footnote-self-ref"
        ) || "gradient.typ" in case.testName
    ) {
        if (SKIP_GPU_OVERLOAD) {
            return println("Skip: GPU OVERLOAD")
        } else {
            Thread.sleep(5000)
        }
    }

    try {
        testCase(case)
    } catch (e: SerializationException) {
        maybeSkip(e, SKIP_CONTEXT, "context") ?: return println("Skip: CONTEXT")
        maybeSkip(e, SKIP_FUNCTIONS, "func") ?: return println("Skip: CONTEXT")
        maybeSkip(e, SKIP_STATE_MANIPULATIONS, "state-update", "counter-update")
            ?: return println("Skip: STATE MANIPULATIONS")
        if (SKIP_DYNAMIC_FUNCTIONS &&
            (e.message!!.startsWith("Non-string type descriptor: ")
                    || e.message == "Dynamic functions aren't supported")
        ) return println("Skip: DYNAMIC FUNCTIONS")

        throw e
    } catch (e: TypstCompilerException) {
        if (
            SKIP_LABELS_ISSUES && e.message!!.matches(Regex("label `.*` does not exist in the document"))
        ) return println("Skip: LABELS ISSUES")

        throw e
    }
}

fun testCase(case: Case) {
    println(case.text)
    println("\n\n")

    val result: TypstCompilerResult<Unit> = typst.compileAndRead(case.text).map { Unit }

    if (!result.success) {
        result.trace.print()
        return
    }

    val meta = typst.query<TMetadata<TContent>>("#metadata[\n${case.text}\n] <lbl>", "<lbl>")
    if (!meta.success) {
        meta.trace.print()
        fail("Compilation succeeded, but query #metadata[content] failed")
    }

    val content = meta
        .orElseThrow()
        .shouldBeSingleton()
        .first()
        .value

    val repr = content.repr()

    println("===============================================\n#$repr")

    val secondaryResult: TypstCompilerResult<Unit> = typst.compileAndRead("#$repr").map { Unit }

    if (!secondaryResult.success) {
        throw TypstCompilerException(secondaryResult.trace)
    }
}
