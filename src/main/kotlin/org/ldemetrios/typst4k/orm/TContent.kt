package org.ldemetrios.typst4k.orm


import kotlinx.serialization.SerialName
import javax.management.Attribute

data object TParbreak : TContent {
    override fun toTypstRepr(): String = "parbreak()"
}

data class TStrong(val delta: TInt? = null, val body: TContent) : TContent {
    override fun toTypstRepr(): String = scriptingRepr(
        "strong",
        listOf(
            "delta" to delta,
            null to body,
        )
    )
}

data class TEmph(val body: TContent) : TContent {
    override fun toTypstRepr(): String = scriptingRepr(
        "emph",
        listOf(
            null to body,
        )
    )
}

data class TQuote(
    val block : TBool? = null,
    val quotes: TAutoOrBool? = null,
    val attributes : TNoneOrLabelOrContent? = null,
    val body : TContent,
) : TContent {
    override fun toTypstRepr(): String = scriptingRepr(
        "quote",
        listOf(
            "block" to block,
            "quotes" to quotes,
            "attributes" to attributes,
            null to body,
        )
    )
}

data class TLink(
    val dest: TStrOrLabelOrDictionary<TIntOrLength>,
    val body : TContent,
) : TContent {
    override fun toTypstRepr(): String = scriptingRepr(
        "link",
        listOf(
            null to dest,
            null to body,
        )
    )
}

data class TRaw(
    val text: TStr,
    val block: TBool? = null,
    val lang: TNoneOrStr? = null,
    val align: TAlignment? = null,
    val syntaxes: TStrOrArray<*>? = null,
    val theme: TNoneOrStr? = null,
    /*@SerialName("tab-size")*/ val tabSize: TInt? = null,
) : TContent {
    override fun toTypstRepr(): String = scriptingRepr(
        "raw",
        listOf(
            null to text,
            "block" to block,
            "lang" to lang,
            "align" to align,
            "syntaxes" to syntaxes,
            "theme" to theme,
            "tab-size" to tabSize,
        )
    )
}

data class THeading(
    val level: TInt? = null,
    val numbering: TNoneOrStr? = null, /*or function*/
    val outlined: TBool? = null,
    val bookmarked: TAutoOrBool? = null,
    val body: TContent,
) : TContent {
    override fun toTypstRepr(): String = scriptingRepr(
        "heading",
        listOf(
            null to body,
            "level" to level,
            "numbering" to numbering,
            "outlined" to outlined,
            "bookmarked" to bookmarked,
        )
    )
}

data class TBibliography(
    val path : TStrOrArray<TStr>,
    val title : TNoneOrAutoOrContent? = null,
    val full : TBool? = null,
    val style : TStr? = null,
) : TContent {
    override fun toTypstRepr(): String = scriptingRepr(
        "bibliography",
        listOf(
            null to path,
            "title" to title,
            "full" to full,
            "style" to style,
        )
    )
}

data class TCite(
    val key: TLabel,
    val supplement: TNoneOrContent? = null,
    val form: TNoneOrStr? = null,
    val style: TAutoOrStr? = null,
) : TContent {
    override fun toTypstRepr(): String = scriptingRepr(
        "cite",
        listOf(
            null to key,
            "supplement" to supplement,
            "form" to form,
            "style" to style,
        )
    )
}

data class TList(
    val tight: TBool? = null,
    val marker: TContentOrArray<TContent>? = null,
    val indent: TLength? = null,
    val bodyIndent: TLength? = null,
    val spacing: TAutoOrRelativeOrFraction? = null,
    val children: TArray<TListItem>,
) : TContent {
    override fun toTypstRepr(): String = scriptingRepr(
        "list",
        listOf(
            "tight" to tight,
            "marker" to marker,
            "indent" to indent,
            "bodyIndent" to bodyIndent,
            "spacing" to spacing,
            null to children,
        )
    )
}

data class TListItem(
    val body: TContent,
) : TContent {
    override fun toTypstRepr(): String = scriptingRepr(
        "list.item",
        listOf(
            null to body,
        )
    )
}

data class TEnum(
    val tight: TBool? = null,
    val numbering: TStr? = null, /*or function*/
    val start: TInt? = null,
    val full: TBool? = null,
    val indent: TLength? = null,
    @SerialName("body-indent") val bodyIndent: TStr? = null,
    val spacing: TAutoOrRelativeOrFraction? = null,
    @SerialName("number-align") val numberAlign: TAlignment? = null,
    val children: TArray<TEnumItem>,
) : TContent {
    override fun toTypstRepr(): String = scriptingRepr(
        "enum",
        listOf(
            "tight" to tight,
            "numbering" to numbering,
            "start" to start,
            "full" to full,
            "indent" to indent,
            "body-indent" to bodyIndent,
            "spacing" to spacing,
            "number-align" to numberAlign,
            null to children,
        )
    )
}

data class TEnumItem(
    val number: TIntOrNone? = null,
    val body: TContent,
) : TContent {
    override fun toTypstRepr(): String = scriptingRepr(
        "enum.item",
        listOf(
            "number" to number,
            null to body,
        )
    )
}

data class TTerms(
    val tight: TBool? = null,
    val separator: TContent? = null,
    val indent: TLength? = null,
    @SerialName("hanging-indent") val hangingIndent: TLength? = null,
    val spacing: TAutoOrRelativeOrFraction? = null,
    val children: TArray<TTermItem>,
) : TContent {
    override fun toTypstRepr(): String = scriptingRepr(
        "terms",
        listOf(
            "tight" to tight,
            "separator" to separator,
            "indent" to indent,
            "hanging-indent" to hangingIndent,
            "spacing" to spacing,
            null to children
        )
    )
}

data class TTermItem(
    val term: TContent,
    val body: TContent,
) : TContent {
    override fun toTypstRepr(): String = scriptingRepr(
        "terms.item",
        listOf(
            null to term,
            null to body,
        )
    )
}

data class TLinebreak(val justify: TBool? = null) : TContent {
    override fun toTypstRepr(): String = scriptingRepr(
        "linebreak",
        listOf(
            "justify" to justify,
        )
    )
}

data class TMetadata<T : TypstValue>(val value: T) : TContent {
    override fun toString(): String = "metadata($value)"

    override fun toTypstRepr(): String = scriptingRepr(
        "metadata",
        listOf(
            null to value,
        )
    )
}

data class TH(
    val amount: TRelativeOrFraction,
    val weak: TBool? = null,
) : TContent {
    override fun toTypstRepr(): String = scriptingRepr(
        "h",
        listOf(
            null to amount,
            "weak" to weak,
        )
    )
}

data class TV(val amount: TRelativeOrFraction, val weak: TBool? = null) : TContent {
    override fun toTypstRepr(): String = scriptingRepr(
        "v",
        listOf(
            null to amount,
            "weak" to weak,
        )
    )
}

data class TText(
    val font: TStrOrArray<*>? = null,
    val fallback: TBool? = null,
    val style: TStr? = null,
    val weight: TIntOrStr? = null,
    val stretch: TRatio? = null,
    val size: TLength? = null,
    val fill: TColorOrGradientOrPattern? = null, //TODO
    val tracking: TLength? = null,
    val spacing: TRelative? = null,
    val cjkLatinSpacing: TNoneOrAuto?? = null,
    val baseline: TLength? = null,
    val overhang: TBool? = null,
    val topEdge: TLengthOrStr? = null,
    val bottomEdge: TLengthOrStr? = null,
    val lang: TStr? = null,
    val region: TNoneOrStr? = null,
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

    override fun toTypstRepr(): String = scriptingRepr(
        "text",
        listOf(
            "font" to font,
            "fallback" to fallback,
            "style" to style,
            "weight" to weight,
            "stretch" to stretch,
            "size" to size,
            "fill" to fill,
            "tracking" to tracking,
            "spacing" to spacing,
            "cjk-latin-spacing" to cjkLatinSpacing,
            "baseline" to baseline,
            "overhang" to overhang,
            "top-edge" to topEdge,
            "bottom-edge" to bottomEdge,
            "lang" to lang,
            "region" to region,
            "script" to script,
            "dir" to dir,
            "hyphenate" to hyphenate,
            "kerning" to kerning,
            "alternates" to alternates,
            "stylistic-set" to stylisticSet,
            "ligatures" to ligatures,
            "discretionary-ligatures" to discretionaryLigatures,
            "historical-ligatures" to historicalLigatures,
            "number-type" to numberType,
            "number-width" to numberWidth,
            "slashed-zero" to slashedZero,
            "fractions" to fractions,
            "features" to features,
            null to text, null to body,
        )
    )
}

data object TSpace : TContent {
    override fun toTypstRepr(): String = "[ ]"
}

data class TSequence(val children: TArray<TContent>) : TContent {
    override fun toTypstRepr(): String {
        return children.list.joinToString(", #", "[ #", " ]") { it.toTypstRepr() }
    }
}

data class TFigure(
    val body: TContent,
    val placement : TNoneOrAutoOrAlignment? = null,
    val caption: TNoneOrContent? = null,
    val kind: TAutoOrStr? = null,
    val supplement: TNoneOrAutoOrContent? = null,
    val numbering: TNoneOrStr? = null,
    val gap: TLength? = null,
    val outlined: TBool? = null,
) : TContent {
    override fun toTypstRepr(): String = scriptingRepr(
        "figure",
        listOf(
            null to body,
            "placement" to placement,
            "caption" to caption,
            "kind" to kind,
            "supplement" to supplement,
            "numbering" to numbering,
            "gap" to gap,
            "outlined" to outlined,
        )
    )
}

data class TFootnote(
    val numbering: TStr? = null,
    val body: TContentOrLabel,
) : TContent {
    override fun toTypstRepr(): String = scriptingRepr(
        "footnote",
        listOf(
            "numbering" to numbering,
            null to body,
        )
    )
}
