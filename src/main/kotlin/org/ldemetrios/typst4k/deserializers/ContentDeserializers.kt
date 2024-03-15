package org.ldemetrios.typst4k.deserializers

import org.ldemetrios.js.*
import org.ldemetrios.typst4k.utils.Lines
import org.ldemetrios.typst4k.orm.*
import org.ldemetrios.typst4k.orm.TText
import org.ldemetrios.utilities.Either
import org.ldemetrios.utilities.cast
import kotlin.reflect.KType
import kotlin.reflect.typeOf

private class ParsingError(val lines: Lines) : Exception()

private fun <T> Either<Lines, T>.assertOK() = when (this) {
    is Either.Left -> throw ParsingError(this.getLeft())
    is Either.Right -> this.getRight()
}

internal class ParameterParser(
    val pool: DeserializerPool,
    val json: JSObject,
    val what: String?,
) {
    inline fun <reified T> parse(what: String): T {
        return pool.deserializeAs(json[what], typeOf<T>(), this.what / what).assertOK().cast<T>()
    }

    inline fun <reified T> parseOptional(what: String): T? {
        return if (json[what] == JSUndefined) null else parse<T>(what)
    }
}

abstract class ContentDeserializer(
    val type: KType,
    val func: String,
) : Deserializer {
    internal abstract fun construct(parser: ParameterParser): Any

    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        return json.assertIs<JSObject>(func) {
            if (JSString(func) != json["func"]) {
                error("Expected func = $func")
            } else {
                try {
                    Either.Right(construct(ParameterParser(pool, it, what)))
                } catch (e: ParsingError) {
                    Either.Left(e.lines)
                }
            }
        }
    }

    override fun canDeserialize(type: KType): Boolean = type.classifier == this.type.classifier
}

object ParbreakDeserializer : ContentDeserializer(
    typeOf<TParbreak>(),
    "parbreak",
) {
    override fun construct(parser: ParameterParser): Any = TParbreak
}

object StrongDeserializer : ContentDeserializer(
    typeOf<TStrong>(),
    "strong",
) {
    override fun construct(parser: ParameterParser): Any = TStrong(
        delta = parser.parseOptional<TInt>("delta"),
        body = parser.parse<TContent>("body"),
    )
}

object EmphDeserializer : ContentDeserializer(
    typeOf<TEmph>(),
    "emphasis",
) {
    override fun construct(parser: ParameterParser): Any = TEmph(
        body = parser.parse<TContent>("body"),
    )
}

object BibliographyDeserializer : ContentDeserializer(
    typeOf<TBibliography>(),
    "bibliography"
) {
    override fun construct(parser: ParameterParser): Any = TBibliography(
        path = parser.parse<TStrOrArray<TStr>>("path"),
        title = parser.parseOptional<TNoneOrAutoOrContent>("title"),
        full = parser.parseOptional<TBool>("full"),
        style = parser.parseOptional<TStr>("style")
    )
}

object RawDeserializer : ContentDeserializer(
    typeOf<TRaw>(),
    "raw",
) {
    override fun construct(parser: ParameterParser): Any = TRaw(
        parser.parse<TStr>("text"),
        block = parser.parseOptional<TBool>("block"),
        lang = parser.parseOptional<TStrOrNone>("lang"),
        align = parser.parseOptional<TAlignment>("align"),
        syntaxes = parser.parseOptional<TStrOrArray<TypstValue>>("syntaxes"),
        theme = parser.parseOptional<TStrOrNone>("theme"),
        tabSize = parser.parseOptional<TInt>("tab-size"),
    )
}

object CiteDeserializer : ContentDeserializer(
    typeOf<TCite>(),
    "cite",
) {
    override fun construct(parser: ParameterParser): Any = TCite(
        key = parser.parse<TLabel>("key"),
        supplement = parser.parseOptional<TNoneOrContent>("supplement"),
        form = parser.parseOptional<TStrOrNone>("form"),
        style = parser.parseOptional<TAutoOrStr>("style")
    )
}

object HeadingDeserializer : ContentDeserializer(
    typeOf<THeading>(),
    "heading",
) {
    override fun construct(parser: ParameterParser): Any = THeading(
        level = parser.parseOptional<TInt>("level"),
        numbering = parser.parseOptional<TStrOrNone>("numbering"),
        outlined = parser.parseOptional<TBool>("outlined"),
        bookmarked = parser.parseOptional<TAutoOrBool>("bookmarked"),
        body = parser.parse<TContent>("body"),
    )
}

object ListDeserializer : ContentDeserializer(
    typeOf<TList>(),
    "list",
) {
    override fun construct(parser: ParameterParser): Any = TList(
        tight = parser.parseOptional<TBool>("tight"),
        marker = parser.parseOptional<TContentOrArray<TContent>>("marker"),
        indent = parser.parseOptional<TLength>("indent"),
        bodyIndent = parser.parseOptional<TLength>("body-indent"),
        spacing = parser.parseOptional<TAutoOrRelativeOrFraction>("spacing"),
        children = parser.parse<TArray<TListItem>>("children"),
    )
}

object EnumDeserializer : ContentDeserializer(
    typeOf<TEnum>(),
    "enum",
) {
    override fun construct(parser: ParameterParser): Any = TEnum(
        tight = parser.parseOptional<TBool>("tight"),
        numbering = parser.parseOptional<TStr>("numbering"),
        start = parser.parseOptional<TInt>("start"),
        full = parser.parseOptional<TBool>("full"),
        indent = parser.parseOptional<TLength>("indent"),
        bodyIndent = parser.parseOptional<TStr>("body-indent"),
        spacing = parser.parseOptional<TAutoOrRelativeOrFraction>("spacing"),
        numberAlign = parser.parseOptional<TAlignment>("number-align"),
        children = parser.parse<TArray<TEnumItem>>("children"),
    )
}

object TermsDeserializer : ContentDeserializer(
    typeOf<TTerms>(),
    "terms",
) {
    override fun construct(parser: ParameterParser): Any = TTerms(
        tight = parser.parseOptional<TBool>("tight"),
        separator = parser.parseOptional<TContent>("separator"),
        indent = parser.parseOptional<TLength>("indent"),
        hangingIndent = parser.parseOptional<TLength>("hanging-indent"),
        spacing = parser.parseOptional<TAutoOrRelativeOrFraction>("spacing"),
        children = parser.parse<TArray<TTermItem>>("children"),
    )
}

object ListItemDeserializer : ContentDeserializer(
    typeOf<TListItem>(),
    "item",
) {
    override fun construct(parser: ParameterParser): Any = TListItem(
        body = parser.parse<TContent>("body"),
    )
}

object EnumItemDeserializer : ContentDeserializer(
    typeOf<TEnumItem>(),
    "item",
) {
    override fun construct(parser: ParameterParser): Any = TEnumItem(
        number = parser.parseOptional<TIntOrNone>("number"),
        body = parser.parse<TContent>("body"),
    )
}

object TermItemDeserializer : ContentDeserializer(
    typeOf<TTermItem>(),
    "item",
) {
    override fun construct(parser: ParameterParser): Any = TTermItem(
        term = parser.parse<TContent>("term"),
        body = parser.parse<TContent>("body"),
    )
}

object LinebreakDeserializer : ContentDeserializer(
    typeOf<TLinebreak>(),
    "block",
) {
    override fun construct(parser: ParameterParser): Any = TLinebreak(
        justify = parser.parseOptional<TBool>("justify"),
    )
}

object HDeserializer : ContentDeserializer(
    typeOf<TH>(),
    "h",
) {
    override fun construct(parser: ParameterParser): Any = TH(
        amount = parser.parse<TRelativeOrFraction>("amount"),
        weak = parser.parseOptional<TBool>("weak"),
    )
}

object VDeserializer : ContentDeserializer(
    typeOf<TV>(),
    "v",
) {
    override fun construct(parser: ParameterParser): Any = TV(
        amount = parser.parse<TRelativeOrFraction>("amount"),
        weak = parser.parseOptional<TBool>("weak"),
    )
}

object TextDeserializer : ContentDeserializer(typeOf<TText>(), "text") {
    override fun construct(parser: ParameterParser): Any = TText(
        font = parser.parseOptional<TStrOrArray<TypstValue>>("font"),
        fallback = parser.parseOptional<TBool>("fallback"),
        style = parser.parseOptional<TStr>("style"),
        weight = parser.parseOptional<TIntOrStr>("weight"),
        stretch = parser.parseOptional<TRatio>("stretch"),
        size = parser.parseOptional<TLength>("size"),
        fill = parser.parseOptional<TColorOrGradientOrPattern>("fill"),
        tracking = parser.parseOptional<TLength>("tracking"),
        spacing = parser.parseOptional<TRelative>("spacing"),
        cjkLatinSpacing = parser.parseOptional<TNoneOrAuto>("cjk-latin-spacing"),
        baseline = parser.parseOptional<TLength>("baseline"),
        overhang = parser.parseOptional<TBool>("overhang"),
        topEdge = parser.parseOptional<TLengthOrStr>("top-edge"),
        bottomEdge = parser.parseOptional<TLengthOrStr>("bottom-edge"),
        lang = parser.parseOptional<TStr>("lang"),
        region = parser.parseOptional<TStrOrNone>("region"),
        script = parser.parseOptional<TAutoOrStr>("script"),
        dir = parser.parseOptional<TAutoOrDirection>("dir"),
        hyphenate = parser.parseOptional<TAutoOrBool>("hyphenate"),
        kerning = parser.parseOptional<TBool>("kerning"),
        alternates = parser.parseOptional<TBool>("alternates"),
        stylisticSet = parser.parseOptional<TIntOrNone>("stylistic-set"),
        ligatures = parser.parseOptional<TBool>("ligatures"),
        discretionaryLigatures = parser.parseOptional<TBool>("discretionary-ligatures"),
        historicalLigatures = parser.parseOptional<TBool>("historical-ligatures"),
        numberType = parser.parseOptional<TAutoOrStr>("number-type"),
        numberWidth = parser.parseOptional<TAutoOrStr>("number-width"),
        slashedZero = parser.parseOptional<TBool>("slashed-zero"),
        fractions = parser.parseOptional<TBool>("fractions"),
        features = parser.parseOptional<TArrayOrDictionary<TypstValue, TypstValue>>("features"),
        body = parser.parseOptional<TContent>("body"),
        text = parser.parseOptional<TStr>("text"),
    )
}

object SpaceDeserializer : ContentDeserializer(typeOf<TSpace>(), "space") {
    override fun construct(parser: ParameterParser): Any = TSpace
}

object SequenceDeserializer : ContentDeserializer(typeOf<TSequence>(), "sequence") {
    override fun construct(parser: ParameterParser): Any = TSequence(
        parser.parse<TArray<TContent>>("children"),
    )
}

object MetadataDeserializer : Deserializer {
    private fun KType.metadataValueType(): KType = when (classifier) {
        typeOf<TMetadata<*>>().classifier -> arguments[0].type
        else -> null
    } ?: typeOf<TypstValue>()

    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        return json.assertIs<JSObject>("metadata") {
            if (JSString("metadata") != json["func"]) {
                error("Expected func = metadata")
            } else {
                pool.deserializeAs(json["value"], type.arguments[0].type!!, what / "value")
                    .mapRight { TMetadata(it.cast()) }
            }
        }
    }

    override fun canDeserialize(type: KType): Boolean = type.classifier == typeOf<TMetadata<*>>().classifier
}

