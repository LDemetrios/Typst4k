package org.ldemetrios.typst4k

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//class TAlignment {
//    start,
//    end,
//    left,
//    center,
//    right,
//    top,
//    horizon,
//    bottom,
//} //TODO

@Serializable
sealed interface TElement {

    @Serializable
    @SerialName("parbreak")
    data object TParbreak : TElement;

    @Serializable
    @SerialName("strong")
    data class TStrong(val delta: Int = 300, val body: TElement) : TElement

    @Serializable
    @SerialName("emph")
    data class TEmph(val body: TElement) : TElement

    @Serializable
    @SerialName("raw")
    data class TRaw(
        val text: String,
        val block: Boolean = false,
        val lang: String? = null,
        val align: String = "start",
        val theme: String? = null,
        @SerialName("tab-size") val tabSize: Int? = 2,
    )

    @Serializable
    @SerialName("heading")
    data class THeading(
        val level: Int = 1,
        val numbering: String? = "1",
        val outlined: Boolean? = true,
//   val bookmarked: Boolean? = null, auto bool
        val body: TElement,
    ) : TElement

    @Serializable
    @SerialName("list")
    data class TList(
        val tight: Boolean = true,
//    marker: content array function,
        val indent: String = "0pt",
        @SerialName("body-indent") val bodyIndent: String = "0.5em",
        val spacing: String = "auto",
        val children: List<TListItem>,
    ) : TElement

    @Serializable
    @SerialName("item")
    data class TListItem(
        val body: TElement,
    )

    @Serializable
    @SerialName("enum")
    data class TEnum(
        val tight: Boolean = true,
        val numbering: String = "1.",
        val start: Int = 1,
        val full: Boolean = false,
        val indent: String = "0pt",
        @SerialName("body-indent") val bodyIndent: String = "0.5em",
        val spacing: String = "auto",
        @SerialName("number-align") val numberAlign: String = "end + top",
        val children: List<TEnumItem>,
    ) : TElement

    @Serializable
    @SerialName("item")
    data class TEnumItem(
        val number: Int? = null,
        val body: TElement,
    )

    @Serializable
    @SerialName("terms")
    data class TTerms(
        val tight: Boolean = true,
        val separator: TElement = TH(amount = "0.6em", weak = true),
        val indent: String = "0pt",
        @SerialName("hanging-indent") val hangingIndent: String = "2em",
        val spacing: String = "auto",
        val children: List<TTermItem>,
    ) : TElement

    @Serializable
    @SerialName("item")
    data class TTermItem(
        val term: TElement,
        val body: TElement,
    )

    @Serializable
    @SerialName("linebreak")
    data class TLinebreak(val justify: Boolean = false) : TElement

    @Serializable
    @SerialName("metadata")
    data class TMetadata<T>(val value: T) : TElement

    @Serializable
    @SerialName("h")
    data class TH(val amount: String, val weak: Boolean = false) : TElement

    @Serializable
    @SerialName("v")
    data class TV(val amount: String, val weak: Boolean = false) : TElement

    @Serializable
    @SerialName("text")
    data class TText(val text: String) : TElement

    @Serializable
    @SerialName("space")
    data object TSpace : TElement

    @Serializable
    @SerialName("sequence")
    data class TSequence(val children: List<TElement>) : TElement
}
