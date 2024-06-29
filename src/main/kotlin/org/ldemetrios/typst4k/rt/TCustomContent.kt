@file:Suppress("PackageDirectoryMismatch")

package org.ldemetrios.typst4k.orm

data class TCustomContent(val function: String, val positional: List<TValue>, val named: Map<String, TValue>) :
    TContent {
    override fun repr(): String {
        val sb = StringBuilder(function)
        sb.append("(")
        for (arg in positional) {
            sb.append(arg.repr())
            sb.append(", ")
        }
        for ((key, value) in named) {
            sb.append(key)
            sb.append(": ")
            sb.append(value.repr())
            sb.append(", ")
        }
        sb.setLength(sb.length - 2)
        sb.append(")")
        return sb.toString()
    }
}