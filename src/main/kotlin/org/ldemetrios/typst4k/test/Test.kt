package org.ldemetrios.typst4k.test

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonClassDiscriminator
import kotlinx.serialization.modules.SerializersModule

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("type")
sealed interface Super

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("func")
sealed interface Sub : Super

@Serializable
@SerialName("A")
data class A(val value: Int) : Super

@Serializable
@SerialName("B")
data class B(val value: Int) : Sub

@Serializable
@SerialName("c")
data class C(@SerialName("Value") val value: Int) : Sub

val module = SerializersModule {
//    polymorphic(Project::class) {
//        subclass(OwnedProject::class)
//    }
}

fun main() {
    val json = Json {
        serializersModule = module
    }
    val x : Super =B(1)
    println(json.encodeToString(x)) // Serializing data of compile-time type Project
}