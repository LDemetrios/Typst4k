package org.ldemetrios.typst4k.orm


import kotlinx.serialization.SerialName
import org.ldemetrios.typst4k.TElement

data object TParbreak : TContent;
data class TStrong(val delta: TInt? = null, val body: TContent) : TContent
data class TEmph(val body: TContent) : TContent
data class TRaw(
    val text: TStr,
    val block: TBool? = null,
    val lang: TStrOrNone? = null,
    val align: TAlignment? = null,
    val syntaxes: TStrOrArray<*>? = null,
    val theme: TStrOrNone? = null,
    /*@SerialName("tab-size")*/ val tabSize: TInt? = null,
) : TContent

data class THeading(
    val level: TInt? = null,
    val numbering: TStrOrNone? = null, /*or function*/
    val outlined: TBool? = null,
    val bookmarked: TAutoOrBool? = null,
    val body: TContent,
) : TContent

data class TList(
    val tight: TBool? = null,
    val marker: TContentOrArray<*>? = null,
    val indent: TLength? = null,
    val bodyIndent: TLength? = null,
    val spacing: TAutoOrRelativeOrFraction? = null,
    val children: List<TListItem>,
) : TContent

data class TListItem(
    val body: TContent,
) : TContent

data class TEnum(
    val tight: TBool? = null,
    val numbering: TStr? = null, /*or function*/
    val start: TInt? = null,
    val full: TBool? = null,
    val indent: TLength? = null,
    @SerialName("body-indent") val bodyIndent: TStr? = null,
    val spacing: TAutoOrRelativeOrFraction? = null,
    @SerialName("number-align") val numberAlign: TAlignment? = null,
    val children: List<TEnumItem>,
) : TContent

data class TEnumItem(
    val number: TIntOrNone? = null,
    val body: TContent,
) : TContent

data class TTerms(
    val tight: TBool? = null,
    val separator: TContent? = null,
    val indent: TLength? = null,
    @SerialName("hanging-indent") val hangingIndent: TLength? = null,
    val spacing: TAutoOrRelativeOrFraction? = null,
    val children: List<TTermItem>,
) : TContent

data class TTermItem(
    val term: TContent,
    val body: TContent,
) : TContent

data class TLinebreak(val justify: TBool? = null) : TContent

data class TMetadata<T : TypstValue>(val value: T) : TContent

data class TH(
    val amount: TRelativeOrFraction,
    val weak: TBool? = null,
) : TContent

data class TV(val amount: TRelativeOrFraction, val weak: TBool? = null) : TElement

data class TText(
    val font: TStrOrArray<*>? = null,
    val fallback: TBool? = null,
    val style: TStr? = null,
    val weight: TIntOrStr? = null,
    val stretch: TRatio? = null,
    val size: TLength? = null,
    val fill: TColorOrGradientOrPattern, //TODO
    val tracking: TLength? = null,
    val spacing: TRelative? = null,
    val cjkLatinSpacing: TNoneOrAuto?? = null,
    val baseline: TLength? = null,
    val overhang: TBool? = null,
    val topEdge: TLengthOrStr? = null,
    val bottomEdge: TLengthOrStr? = null,
    val lang: TStr? = null,
    val region: TStrOrNone? = null,
    val script: TAutoOrStr? = null,
    val dir: TAutoOrDirection? = null,
    val hyphenate: TAutoOrBool? = null,
    val kerning: TBool? = null,
    val alternates: TBool? = null,
    val stylisticSet: TIntOrNone? = null,
    val ligatures: TBool? = null,
    val discretionaryLigatures: TBool? = null,
    val historicalLigatures: TBool? = null,
    val numberType: TAutoOrStr? = null,
    val numberWidth: TAutoOrStr? = null,
    val slashedZero: TBool? = null,
    val fractions: TBool? = null,
    val features: TArrayOrDictionary<*, *>? = null,
    val body: TContent? = null,
    val text: TStr? = null,
) : TContent {
    init {
        require(body != null || text != null) { "Either body or text is required" }
    }
}

data object TSpace : TContent
data class TSequence(val children: List<TContent>) : TContent
