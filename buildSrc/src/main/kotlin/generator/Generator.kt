package generator

import org.ldemetrios.utilities.cast
import org.ldemetrios.utilities.castOrNull
import parsers.*
import java.io.File
import java.util.*

fun kebabToTitleCamel(string: String): String {
    val words = string.split(".").last().split("-")
    val result = StringBuilder()
    for (word in words) {
        result.append(word.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
    }
    return result.toString()
}

fun kebabToLowerCamel(string: String): String {
    val x = kebabToTitleCamel(string)
        .replaceFirstChar { if (it.isUpperCase()) it.lowercase(Locale.getDefault()) else it.toString() }
    return if(x == "class") "clazz" else x
}


fun genericParametersOfUnion(union: Set<String>, allGenerics: List<ConcreteType>): List<TypeParameter> {
    val genericParametersOfUnion = mutableListOf<TypeParameter>()
    for (variant in union) {
        val generic = allGenerics.find { it.name == variant }
        if (generic == null) continue
        genericParametersOfUnion.addAll(generic.params)
    }
    return genericParametersOfUnion
}

fun generate(
    prefix: String,
    common: String,
    place: String,
    pack: String,

    primitives: Map<String, Primitive>,
    specformat: List<String>,
    classDefinitions: Map<String, ClassDefinition>,
) {
    val imports = """
         |import kotlinx.serialization.*
         |import org.ldemetrios.typst4k.rt.*
         |import org.ldemetrios.utilities.cast
    """.trimMargin()
    generateGeneric(
        prefix,
        common,
        place,
        pack,
        imports,
        primitives,
        specformat,
        classDefinitions
    )

    generateNonGeneric(
        prefix,
        common,
        place,
        pack,
        "$imports\nimport $pack.*\n",
        primitives,
        specformat,
        classDefinitions
    )

}

fun generateGeneric(
    prefix: String,
    common: String,
    place: String,
    pack: String,
    imports: String,
    primitives: Map<String, Primitive>,
    specformat: List<String>,
    classDefinitions: Map<String, ClassDefinition>,
) {
    val prelude = "package $pack\n\n$imports\n\n"

    val allGenerics =
        primitives.values.filter { it.type.isGeneric() }.map { it.type } +
                classDefinitions.values.filter { it.type.isGeneric() }.map { it.type }


    val allUnions = mutableSetOf<Set<String>>() // List of names of concrete types for each Union

    fun addUnions(type: Type) {
        when (type) {
            is ConcreteType -> for (param in type.params) addUnions(param.param)
            is UnionType -> {
                allUnions.add(type.variants.map { it.cast<ConcreteType>().name }.toSet())
                for (variant in type.variants) {
                    addUnions(variant)
                }
            }

            is AnyType -> Unit
        }
    }

    for (it in classDefinitions) {
        for (elem in it.value.classElements) {
            addUnions(elem.type)
        }
    }

    val general = StringBuilder(
        prelude + """
            |@Serializable
            |sealed interface $common {
            |   fun repr() : String
            |}
            |
        """.trimMargin() + "\n\n"
    )

    for (union in allUnions) {
        general.append("@Serializable\nsealed interface $prefix")
        general.append(union.sorted().joinToString("Or", transform = ::kebabToTitleCamel))

        val generics = genericParametersOfUnion(union, allGenerics)
        if (generics.size > 0) {
            general.append(
                "<" + generics.joinToString(", ") { "$it : $common" } + ">"
            )
        }

        general.append(" : $common")

        unionTypeInterfaces(allUnions, union, general, prefix, generics, common, allGenerics)

        general.append("\n")
    }

    File("$place/base/$common.kt").run {
        parentFile.mkdirs()
        writeText(general.toString())
    }

    val parents = classDefinitions.values.mapNotNull { it.parent }
    val allNames = primitives.keys + classDefinitions.keys + parents.map { it.name }


    for ((primName, primitive) in primitives) {
        val code = StringBuilder(
            prelude
        )

        code.append("@SerialName(\"$primName\")\n@Serializable\n")

        code.append("data class $prefix${kebabToTitleCamel(primName)}")
        if (primitive.type.isGeneric()) {
            code.append("<")
            code.append(primitive.type.params.joinToString(", ") { "$it : $common" })
            code.append(">")
        }

        code.append("(val value : ")
        code.append(primitive.native)
        code.append(")")

        code.append(" : $common")
        if (primitive.delegatable) {
            code.append(", ")
            code.append(primitive.native)
            code.append(" by value")
        }

        val union = setOf(primName)
        val generics = genericParametersOfUnion(union, allGenerics)
        unionTypeInterfaces(allUnions, union, code, prefix, generics, common, allGenerics)

        code.append(" {\n    override fun repr() : String = RT.reprOf(value)\n}\n")

        File("$place/base/${kebabToTitleCamel(primName)}.kt").run {
            parentFile.mkdirs()
            writeText(code.toString())
        }
    }



    for (parent in parents) {
        val code = StringBuilder(
            prelude
        )

        code.append("@Serializable\nsealed interface $prefix${kebabToTitleCamel(parent.name)}")
        // No generic parents omg

        code.append(" : $common")

        val union = setOf(parent.name)
        val generics = genericParametersOfUnion(union, allGenerics)
        unionTypeInterfaces(allUnions, union, code, prefix, generics, common, allGenerics)

        code.append("\n")

        File("$place/${kebabToTitleCamel(parent.name)}.kt").run {
            parentFile.mkdirs()
            writeText(code.toString())
        }
    }


    for ((className, classDefinition) in classDefinitions) {
        val backwarsParentList = run {
            val result = mutableListOf<String>()
            var current = classDefinition.parent
            while (current != null) {
                result.add(current.name)
                current = classDefinitions[current.name]?.parent
            }
            result.reversed()
        }

        val code = StringBuilder(
            prelude
        )
        code.append("@SerialName(\"$className\")\n@Serializable\n")

        if (classDefinition.classElements.isEmpty()) {
            code.append("data object $prefix${kebabToTitleCamel(className)}")
        } else {
            code.append("data class $prefix${kebabToTitleCamel(className)}")
            if (classDefinition.type.isGeneric()) {
                code.append("<")
                code.append(classDefinition.type.params.joinToString(", ") { "$it : $common" })
                code.append(">")
            }

            code.append("(\n")
            classDefinition.classElements.forEach {
                code.append("    @SerialName(\"${it.name}\") val ")
                code.append(kebabToLowerCamel(it.name))
                code.append(" : ")
                code.formatType(it.type, prefix, allNames)
                if (!it.required) {
                    code.append("? = null")
                }
                code.append(", \n")
            }
            code.append(")")
        }

        if (classDefinition.parent != null) {
            code.append(" : $prefix${kebabToTitleCamel(classDefinition.parent.name)}")
        } else code.append(" : $common")

        val union = setOf(className)
        val generics = genericParametersOfUnion(union, allGenerics)
        unionTypeInterfaces(allUnions, union, code, prefix, generics, common, allGenerics)

        code.append("\n{\n    override fun repr() : String = ")

        if (specformat.contains(className)) {
            code.append("RT.reprOf(this)")
        } else {
            code.append("RT.structRepr(\"$className\", ")
            classDefinition.classElements.forEach {
                if (it.positional) {
                    code.append("null")
                } else {
                    code.append("\"")
                    code.append(it.name)
                    code.append("\"")
                }
                code.append(" to ")
                code.append(kebabToLowerCamel(it.name))
                code.append(", ")
            }
            code.append(")")
        }
        code.append("\n}\n")

        val folders = backwarsParentList.joinToString { it.replace("-", "_") + "s/" }
        File(
            "$place/$folders/${kebabToTitleCamel(className)}.kt"
        ).run {
            parentFile.mkdirs()
            writeText(code.toString())
        }
    }
}


fun generateNonGeneric(
    prefix: String,
    common: String,
    place: String,
    pack: String,
    imports: String,
    primitives: Map<String, Primitive>,
    specformat: List<String>,
    classDefinitions: Map<String, ClassDefinition>,
) {

    val prelude = "@file:Suppress(\"UNNECESSARY_SAFE_CALL\", \"KotlinRedundantDiagnosticSuppress\")\n\npackage $pack.nongeneric\n\n$imports\n\n"

    val allGenerics =
        primitives.values.filter { it.type.isGeneric() }.map { it.type } +
                classDefinitions.values.filter { it.type.isGeneric() }.map { it.type }


    val allUnions = mutableSetOf<Set<String>>() // List of names of concrete types for each Union

    fun addUnions(type: Type) {
        when (type) {
            is ConcreteType -> for (param in type.params) addUnions(param.param)
            is UnionType -> {
                allUnions.add(type.variants.map { it.cast<ConcreteType>().name }.toSet())
                for (variant in type.variants) {
                    addUnions(variant)
                }
            }

            is AnyType -> Unit
        }
    }

    for (it in classDefinitions) {
        for (elem in it.value.classElements) {
            addUnions(elem.type)
        }
    }

    val general = StringBuilder(
        prelude + """
            |@Serializable
            |sealed interface NG$common {
            |   fun convert() : $common
            |}
            |
        """.trimMargin() + "\n\n"
    )

    for (union in allUnions) {
        general.append("@Serializable\nsealed interface NG$prefix")
        general.append(union.sorted().joinToString("Or", transform = ::kebabToTitleCamel))

        val generics = genericParametersOfUnion(union, allGenerics)
        if (generics.size > 0) {
            general.append(
                "<" + generics.joinToString(", ") { "$it : $common" } + ">"
            )
        }

        general.append(" : NG$common")

        unionTypeInterfaces(allUnions, union, general, "NG" + prefix, generics, "NG" + common, allGenerics)

        general.append("\n")
    }

    File("$place/nongeneric/base/NG_$common.kt").run {
        parentFile.mkdirs()
        writeText(general.toString())
    }

    val parents = classDefinitions.values.mapNotNull { it.parent }
    val allNames = primitives.keys + classDefinitions.keys + parents.map { it.name }

    val AnyParam = TypeParameter(ConcreteType("NG$common"), "");

    fun removeGenerics(type: Type, generics: List<Type>): Type {
        return when (type) {
            is ConcreteType -> {
                val params = type.params.map {
                    if (it.param in generics) AnyParam
                    else TypeParameter(removeGenerics(it.param, generics), it.variance)
                }
                ConcreteType(type.name, params)
            }
            is UnionType -> UnionType(type.variants.map { removeGenerics(it, generics) })
            is AnyType -> AnyParam.param
        }
    }

    for ((primName, primitive) in primitives) {
        val thisGenerics = primitive.type.params.map{it.param}

        val code = StringBuilder(
            prelude
        )

        code.append("@SerialName(\"$primName\")\n@Serializable\n")

        code.append("data class NG$prefix${kebabToTitleCamel(primName)}")
        if (primitive.type.isGeneric()) {
            code.append("<")
            code.append(primitive.type.params.joinToString(", ") { "$it : $common" })
            code.append(">")
        }

        code.append("(val value : ")
        code.append(removeGenerics(primitive.native, thisGenerics))
        code.append(")")

        code.append(" : NG$common")
        if (primitive.delegatable) {
            code.append(", ")
            code.append(removeGenerics(primitive.native, thisGenerics))
            code.append(" by value")
        }

        val union = setOf(primName)
        val generics = genericParametersOfUnion(union, allGenerics)
        unionTypeInterfaces(allUnions, union, code, "NG" + prefix, generics, "NG" + common, allGenerics)

        code.append(" {\n    override fun convert() = ")
        if (primitive.type.isGeneric()) {
            code.append("RT.convert${kebabToTitleCamel(primName)}<")
            code.append(primitive.type.params.joinToString(", ") { it.param.toString() })
            code.append(">(value)")
        } else {
            code.append("$pack.$prefix${kebabToTitleCamel(primName)}(value)")
        }
        code.append("\n}\n")

        File("$place/nongeneric/base/NG_${kebabToTitleCamel(primName)}.kt").run {
            parentFile.mkdirs()
            writeText(code.toString())
        }
    }



    for (parent in parents) {
        val code = StringBuilder(
            prelude
        )

        code.append("@Serializable\nsealed interface NG$prefix${kebabToTitleCamel(parent.name)}")
        // No generic parents omg

        code.append(" : NG$common")

        val union = setOf(parent.name)
        val generics = genericParametersOfUnion(union, allGenerics)
        unionTypeInterfaces(allUnions, union, code, "NG" + prefix, generics, "NG" + common, allGenerics)

        code.append("\n")

        File("$place/nongeneric/NG_${kebabToTitleCamel(parent.name)}.kt").run {
            parentFile.mkdirs()
            writeText(code.toString())
        }
    }


    for ((className, classDefinition) in classDefinitions) {
        val thisGenerics = classDefinition.type.params.map{it.param}

        val backwarsParentList = run {
            val result = mutableListOf<String>()
            var current = classDefinition.parent
            while (current != null) {
                result.add(current.name)
                current = classDefinitions[current.name]?.parent
            }
            result.reversed()
        }

        val code = StringBuilder(
            prelude
        )
        code.append("@SerialName(\"$className\")\n@Serializable\n")

        if (classDefinition.classElements.isEmpty()) {
            code.append("data object NG$prefix${kebabToTitleCamel(className)}")
        } else {
            code.append("data class NG$prefix${kebabToTitleCamel(className)}")
            if (classDefinition.type.isGeneric()) {
                code.append("<")
                code.append(classDefinition.type.params.joinToString(", ") { "$it : $common" })
                code.append(">")
            }

            code.append("(\n")
            classDefinition.classElements.forEach {
                code.append("    @SerialName(\"${it.name}\") val ")
                code.append(kebabToLowerCamel(it.name))
                code.append(" : NG")
                if(it.type in thisGenerics){
                    code.append(common)
                } else {
                    code.formatType(it.type, prefix, allNames)
                }
                if (!it.required) {
                    code.append("? = null")
                }
                code.append(", \n")
            }
            code.append(")")
        }

        if (classDefinition.parent != null) {
            code.append(" : NG$prefix${kebabToTitleCamel(classDefinition.parent.name)}")
        } else code.append(" : NG$common")

        val union = setOf(className)
        val generics = genericParametersOfUnion(union, allGenerics)
        unionTypeInterfaces(allUnions, union, code, "NG" + prefix, generics, "NG" + common, allGenerics)

        code.append("\n{\n    override fun convert() = ")

        if (classDefinition.type.isGeneric()) {
            code.append("RT.convert${kebabToTitleCamel(className)}<")
            code.append(classDefinition.type.params.joinToString(", ") { it.param.toString() })
            code.append(">(this)")
        } else {
            code.append("$prefix${kebabToTitleCamel(className)}")
            if (classDefinition.classElements.isNotEmpty()) code.append("(")
            code.append(classDefinition.classElements.joinToString(", ") { kebabToLowerCamel( it.name) + "?.convert().cast()" })
            if (classDefinition.classElements.isNotEmpty()) code.append(")")
        }
        code.append("\n}\n")

        val folders = backwarsParentList.joinToString { it.replace("-", "_") + "s/" }
        File(
            "$place/nongeneric/$folders/NG_${kebabToTitleCamel(className)}.kt"
        ).run {
            parentFile.mkdirs()
            writeText(code.toString())
        }
    }

}

private fun StringBuilder.formatType(type: Type, prefix: String, allNames: Set<String>) {
    val simplified = if (type is UnionType) type.flatten().flattenSingle() else type
    when (simplified) {
        is UnionType -> {
            append(prefix)
            val variants = simplified.variants.sortedBy { it.toString() }
            append(variants.joinToString("Or") { kebabToTitleCamel(it.cast<ConcreteType>().name) })
            if (simplified.variants.any { it.cast<ConcreteType>().params.isNotEmpty() }) {
                append("<")
                val generics = variants.mapNotNull { it.castOrNull<ConcreteType>()?.params }.flatten()
                for (param in generics) {
                    formatType(param.param, prefix, allNames)
                    append(", ")
                }
                append(">")
            }
        }

        is ConcreteType -> {
            if (simplified.name in allNames) append(prefix)
            append(kebabToTitleCamel(simplified.name))
            if (simplified.params.isNotEmpty()) {
                append("<")
                for (param in simplified.params) {
                    formatType(param.param, prefix, allNames)
                    append(", ")
                }
                append(">")
            }
        }

        is AnyType -> append("*")
    }
}

private fun unionTypeInterfaces(
    allUnions: MutableSet<Set<String>>,
    union: Set<String>,
    code: StringBuilder,
    prefix: String,
    generics: List<TypeParameter>,
    common: String,
    allGenerics: List<ConcreteType>
) {
    allUnions.filter { union.isSubsetOf(it) && union != it }.forEach { superset ->
        code.append(", \n    ").append(prefix)
        code.append(superset.sorted().joinToString("Or", transform = ::kebabToTitleCamel))
        val superGenerics = genericParametersOfUnion(superset, allGenerics)
        if (superGenerics.isNotEmpty()) {
            code.append(
                "<" + superGenerics.joinToString(", ") {
                    if (it in generics) it.param.toString()
                    else if (it.variance == "out") "Nothing"
                    else if (it.variance == "in") common
                    else throw AssertionError()
                } + ">"
            )
        }
    }
}

fun kindaMain(
    datamodelFile: String,
    prefix: String,
    commonInterfaceName: String,
    location: String,
    packageName: String,
) {
    val data = File(datamodelFile).readText()
    val comrem = CommentsRemover(data)
    comrem.parse()
    val parser = ModelParser(comrem.result.toString())
    parser.parse()

    generate(
        prefix = prefix,
        common = commonInterfaceName,
        place = location,
        pack = packageName,
        primitives = parser.primitives,
        specformat = parser.specformat,
        classDefinitions = parser.classDefinitions,
    )
}

fun <T> Set<T>.isSubsetOf(other: Set<T>) = this.all { it in other }

fun main(){
    val rootDir = "/home/ldemetrios/Workspace/Typst4k"
    kindaMain(
        datamodelFile = "$rootDir/datamodel",
        prefix = "T",
        commonInterfaceName = "TValue",
        location = "$rootDir/src/main/kotlin/org/ldemetrios/typst4k/orm",
        packageName = "org.ldemetrios.typst4k.orm",

        )
}