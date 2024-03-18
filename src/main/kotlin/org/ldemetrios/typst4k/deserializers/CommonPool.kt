package org.ldemetrios.typst4k.deserializers

import org.ldemetrios.js.JSParser
import org.ldemetrios.typst4k.orm.*

val TypstDeserializerPool = run {
    val pool = DeserializerPool()
    pool.register<TIntOrRatio>(TIntOrRatioDeserializer)
    pool.register<TFloatOrRatio>(TFloatOrRatioDeserializer)
    pool.register<TRelativeOrFraction>(TRelativeOrFractionDeserializer)
    pool.register<TNoneOrStr>(TNoneOrStrDeserializer)
    pool.register<TStrOrArray<*>>(TStrOrArrayDeserializer)
    pool.register<TAutoOrBool>(TAutoOrBoolDeserializer)
    pool.register<TContentOrArray<*>>(TContentOrArrayDeserializer)
    pool.register<TAutoOrArray<*>>(TAutoOrArrayDeserializer)
    pool.register<TAutoOrRelativeOrFraction>(TAutoOrRelativeOrFractionDeserializer)
    pool.register<TIntOrNone>(TIntOrNoneDeserializer)
    pool.register<TIntOrStr>(TIntOrStrDeserializer)
    pool.register<TColorOrGradientOrPattern>(TColorOrGradientOrPatternDeserializer)
    pool.register<TNoneOrAuto>(TNoneOrAutoDeserializer)
    pool.register<TLengthOrStr>(TLengthOrStrDeserializer)
    pool.register<TAutoOrStr>(TAutoOrStrDeserializer)
    pool.register<TAutoOrDirection>(TAutoOrDirectionDeserializer)
    pool.register<TArrayOrDictionary<*, *>>(TArrayOrDictionaryDeserializer)
    pool.register<TNoneOrAutoOrContent>(TNoneOrAutoOrContentDeserializer)
    pool.register<TNoneOrAutoOrContent>(TNoneOrContentDeserializer)
    pool.register<TLengthOrAuto>(TLengthOrAutoDeserializer)
    pool.register<TNoneOrAutoOrAlignment>(TNoneOrAutoOrAlignmentDeserializer)
    pool.register<TContentOrLabel>(TContentOrLabelDeserializer)
    pool.register<TIntOrLength>(TIntOrLengthDeserializer)
    pool.register<TStrOrLabelOrDictionary<*>>(TStrOrLabelOrDictionaryDeserializer)
    pool.register<TNoneOrLabelOrContent>(TNoneOrLabelOrContentDeserializer)

    pool.register<TypstValue>(EitherValueDeserializer)
    pool.register<TContent>(EitherContentDeserializer)

    pool.register<TParbreak>(ParbreakDeserializer)
    pool.register<TStrong>(StrongDeserializer)
    pool.register<TEmph>(EmphDeserializer)
    pool.register<TRaw>(RawDeserializer)
    pool.register<THeading>(HeadingDeserializer)
    pool.register<TList>(ListDeserializer)
    pool.register<TEnum>(EnumDeserializer)
    pool.register<TTerms>(TermsDeserializer)
    pool.register<TListItem>(ListItemDeserializer)
    pool.register<TEnumItem>(EnumItemDeserializer)
    pool.register<TTermItem>(TermItemDeserializer)
    pool.register<TLinebreak>(LinebreakDeserializer)
    pool.register<TH>(HDeserializer)
    pool.register<TV>(VDeserializer)
    pool.register<TText>(TextDeserializer)
    pool.register<TSpace>(SpaceDeserializer)
    pool.register<TSequence>(SequenceDeserializer)
    pool.register<TMetadata<*>>(MetadataDeserializer)

    pool.register<TNone>(NoneDeserializer)
    pool.register<TAuto>(AutoDeserializer)
    pool.register<TAngle>(AngleDeserializer)
    pool.register<TArray<*>>(ArrayDeserializer)
    pool.register<TBool>(BoolDeserializer)
    pool.register<TDatetime>(DatetimeDeserializer)
    pool.register<TDictionary<*>>(DictionaryDeserializer)
    pool.register<TDuration>(DurationDeserializer)
    pool.register<TFloat>(FloatDeserializer)
    pool.register<TFraction>(FractionDeserializer)
    pool.register<TInt>(IntDeserializer)
    pool.register<TLabel>(LabelDeserializer)
    pool.register<TRegex>(RegexDeserializer)
    pool.register<TStr>(StrDeserializer)
    pool.register<TVersion>(VersionDeserializer)
    pool.register<TLength>(LengthDeserializer)
    pool.register<TRatio>(RatioDeserializer)
    pool.register<TRelative>(RelativeDeserializer)
    pool.register<TDirection>(DirectionDeserializer)
    pool.register<TAlignment>(AlignmentDeserializer)
    pool.register<TColor>(ColorDeserializer)

    pool
}

fun main() {
    TypstDeserializerPool.deserialize<TArray<TMetadata<TypstValue>>>(
        JSParser.parseArray(
            """
        [
          {
            "func": "metadata",
            "value": {
              "none": null,
              "auto": "auto",
              "angles": {
                "degrees": "2deg",
                "radians": "114.59deg"
              },
              "array": [
                1,
                2,
                3
              ],
              "bool": true,
              "bytes": "Bytes(3)",
              "datetime": "datetime(year: 2024, month: 3, day: 8)",
              "dictionary": {
                "b": 1,
                "a": 2
              },
              "duration": "duration(weeks: 2827, days: 1)",
              "floats": [
                1.0,
                -0.0,
                10000.0,
                null,
                null
              ],
              "fractions": [
                "1fr",
                "1.5fr"
              ],
              "ints": [
                1,
                2,
                9223372036854775807
              ],
              "label": "<b>",
              "regex": "regex(\"\\r\\n|\\r|\\n\")",
              "str": "str",
              "version": "version(1, 2, 3)",
              "type": "integer",
              "relatives": [
                "100% + 50pt + -2em",
                "-50% + 106.69pt",
                "50% + 141.73pt",
                "50% + 65.2pt",
                "-25% + 28.35pt"
              ],
              "lengths": [
                "56.69pt + 2em",
                "51.02pt",
                "75.83pt + 1em"
              ],
              "ratio": "100%",
              "alignments": [
                "left",
                "left + top"
              ]
            },
            "label": "<lbl>"
          }
        ]
    """.trimIndent()
        )
    ).also(::println)
}

