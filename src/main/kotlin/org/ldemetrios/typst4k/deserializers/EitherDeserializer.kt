package org.ldemetrios.typst4k.deserializers

import org.ldemetrios.js.*
import org.ldemetrios.typst4k.orm.TLengthOrAuto
import org.ldemetrios.typst4k.orm.TypstValue
import org.ldemetrios.typst4k.utils.Lines
import org.ldemetrios.typst4k.utils.join
import org.ldemetrios.utilities.Either
import kotlin.reflect.KType

data class EitherDeserializer(val variants: List<Deserializer>, val name: String? = null) : Deserializer {
    constructor(vararg variants: Deserializer) : this(variants.toList())

    override fun deserializeAs(pool: DeserializerPool, json: JSStuff, type: KType, what: String?): Either<Lines, Any> {
        val err = mutableListOf<Lines>()
        for (deserializer in variants) {
            val res = deserializer.deserializeAs(pool, json, type, what)
            if (res.isLeft()) err.add(res.getLeft())
            else return res
        }
        val errHeavy = Lines.of("$this failed deserializing" + if (what == null) "" else " $what")
        return Either.Left(
            errHeavy +
                    err.join().tab()
        )
    }

    override fun canDeserialize(type: KType): Boolean = variants.any { it.canDeserialize(type) }

    override fun toString(): String = name ?: "EitherDeser(${variants.joinToString { it.toString() }})"
}

val EitherContentDeserializer = EitherDeserializer(
    listOf(
        ParbreakDeserializer,
        LinkDeserializer,
        QuoteDeserializer,
        StrongDeserializer,
        EmphDeserializer,
        RawDeserializer,
        HeadingDeserializer,
        CiteDeserializer,
        ListDeserializer,
        EnumDeserializer,
        TermsDeserializer,
        ListItemDeserializer,
        EnumItemDeserializer,
        TermItemDeserializer,
        LinebreakDeserializer,
        HDeserializer,
        VDeserializer,
        TextDeserializer,
        SpaceDeserializer,
        SequenceDeserializer,
        MetadataDeserializer,
        BibliographyDeserializer,
        FigureDeserializer,
        FootnoteDeserializer
    ), name = "ContentDeserializer"
)

val EitherValueDeserializer = EitherDeserializer(
    listOf(
        NoneDeserializer,
        AutoDeserializer,
        AngleDeserializer,
        ArrayDeserializer,
        BoolDeserializer,
        DatetimeDeserializer,
        DictionaryDeserializer,
        DurationDeserializer,
        FloatDeserializer,
        FractionDeserializer,
        IntDeserializer,
        LabelDeserializer,
        RegexDeserializer,
        VersionDeserializer,
        LengthDeserializer,
        RatioDeserializer,
        RelativeDeserializer,
        DirectionDeserializer,
        AlignmentDeserializer,
        ColorDeserializer,
        EitherContentDeserializer,
        StrDeserializer,
    ), name = "ValueDeserializer"
)

val TIntOrRatioDeserializer = EitherDeserializer(listOf(IntDeserializer, RatioDeserializer), "(Int|Ratio)Deserializer")
val TFloatOrRatioDeserializer =
    EitherDeserializer(listOf(FloatDeserializer, RatioDeserializer), "(Float|Ratio)Deserializer")
val TRelativeOrFractionDeserializer =
    EitherDeserializer(listOf(RelativeDeserializer, FractionDeserializer), "(Relative|Fraction)Deserializer")
val TNoneOrStrDeserializer = EitherDeserializer(listOf(NoneDeserializer, StrDeserializer), "(Str|None)Deserializer")
val TStrOrArrayDeserializer = EitherDeserializer(listOf(ArrayDeserializer, StrDeserializer), "(Str|Array)Deserializer")
val TAutoOrBoolDeserializer = EitherDeserializer(listOf(AutoDeserializer, BoolDeserializer), "(Auto|Bool)Deserializer")
val TContentOrArrayDeserializer =
    EitherDeserializer(listOf(EitherContentDeserializer, ArrayDeserializer), "(Content|Array)Deserializer")
val TAutoOrArrayDeserializer =
    EitherDeserializer(listOf(AutoDeserializer, ArrayDeserializer), "(Auto|Array)Deserializer")
val TAutoOrRelativeOrFractionDeserializer = EitherDeserializer(
    listOf(AutoDeserializer, RelativeDeserializer, FractionDeserializer),
    "(Auto|Relative|Fraction)Deserializer"
)
val TIntOrNoneDeserializer = EitherDeserializer(listOf(IntDeserializer, NoneDeserializer), "(Int|None)Deserializer")
val TIntOrStrDeserializer = EitherDeserializer(listOf(IntDeserializer, StrDeserializer), "(Int|Str)Deserializer")
val TColorOrGradientOrPatternDeserializer = EitherDeserializer(
    listOf(ColorDeserializer/*, TODO GradientDeserializer, PatternDeserializer*/),
    "(Color|Gradient|Pattern)Deserializer"
)
val TNoneOrAutoDeserializer = EitherDeserializer(listOf(NoneDeserializer, AutoDeserializer), "(None|Auto)Deserializer")
val TLengthOrStrDeserializer =
    EitherDeserializer(listOf(LengthDeserializer, StrDeserializer), "(Length|Str)Deserializer")
val TAutoOrStrDeserializer = EitherDeserializer(listOf(AutoDeserializer, StrDeserializer), "(Auto|Str)Deserializer")
val TAutoOrDirectionDeserializer =
    EitherDeserializer(listOf(AutoDeserializer, DirectionDeserializer), "(Auto|Direction)Deserializer")
val TArrayOrDictionaryDeserializer =
    EitherDeserializer(listOf(ArrayDeserializer, DictionaryDeserializer), "(Array|Dictionary)Deserializer")
val TNoneOrAutoOrContentDeserializer =
    EitherDeserializer(listOf(NoneDeserializer, AutoDeserializer, EitherContentDeserializer), "(None|Content|Auto)Deserializer")
val TNoneOrContentDeserializer =    EitherDeserializer(listOf(NoneDeserializer, EitherContentDeserializer), "(None|Content)Deserializer")
val TLengthOrAutoDeserializer =    EitherDeserializer(listOf(LengthDeserializer, AutoDeserializer), "(Length|Auto)Deserializer")

val TNoneOrAutoOrAlignmentDeserializer =    EitherDeserializer(listOf(NoneDeserializer, AutoDeserializer, AlignmentDeserializer), "(None|Auto|Alignment)Deserializer")
val TContentOrLabelDeserializer =    EitherDeserializer(listOf(EitherContentDeserializer, LabelDeserializer), "(Content|Label)Deserializer")
val TIntOrLengthDeserializer =    EitherDeserializer(listOf(IntDeserializer, LengthDeserializer), "(Int|Length)Deserializer")
val TStrOrLabelOrDictionaryDeserializer =    EitherDeserializer(listOf(StrDeserializer, LabelDeserializer, DictionaryDeserializer), "(Str|Label|Dictionary)Deserializer")
val TNoneOrLabelOrContentDeserializer =    EitherDeserializer(listOf(NoneDeserializer, LabelDeserializer, EitherContentDeserializer), "(None|Label|Content)Deserializer")
