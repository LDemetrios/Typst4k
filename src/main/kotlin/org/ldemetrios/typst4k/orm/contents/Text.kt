package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("text")
@Serializable
data class TText(
    @SerialName("font") val font : TArrayOrStr<TStr, >? = null, 
    @SerialName("fallback") val fallback : TBool? = null, 
    @SerialName("style") val style : TStr? = null, 
    @SerialName("weight") val weight : TIntOrStr? = null, 
    @SerialName("stretch") val stretch : TRatio? = null, 
    @SerialName("size") val size : TLength? = null, 
    @SerialName("fill") val fill : TColorOrGradientOrPattern? = null, 
    @SerialName("stroke") val stroke : TColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<*, >? = null, 
    @SerialName("tracking") val tracking : TLength? = null, 
    @SerialName("spacing") val spacing : TRelative? = null, 
    @SerialName("cjk-latin-spacing") val cjkLatinSpacing : TAutoOrNone? = null, 
    @SerialName("baseline") val baseline : TLength? = null, 
    @SerialName("overhang") val overhang : TBool? = null, 
    @SerialName("top-edge") val topEdge : TLengthOrStr? = null, 
    @SerialName("bottom-edge") val bottomEdge : TLengthOrStr? = null, 
    @SerialName("lang") val lang : TStr? = null, 
    @SerialName("region") val region : TNoneOrStr? = null, 
    @SerialName("script") val script : TAutoOrStr? = null, 
    @SerialName("dir") val dir : TAutoOrDirection? = null, 
    @SerialName("hyphenate") val hyphenate : TAutoOrBool? = null, 
    @SerialName("kerning") val kerning : TBool? = null, 
    @SerialName("alternates") val alternates : TBool? = null, 
    @SerialName("stylistic-set") val stylisticSet : TIntOrNone? = null, 
    @SerialName("ligatures") val ligatures : TBool? = null, 
    @SerialName("discretionary-ligatures") val discretionaryLigatures : TBool? = null, 
    @SerialName("historical-ligatures") val historicalLigatures : TBool? = null, 
    @SerialName("number-type") val numberType : TAutoOrStr? = null, 
    @SerialName("number-width") val numberWidth : TAutoOrStr? = null, 
    @SerialName("slashed-zero") val slashedZero : TBool? = null, 
    @SerialName("fractions") val fractions : TBool? = null, 
    @SerialName("features") val features : TArrayOrDictionary<TStr, TInt, >? = null, 
    @SerialName("body") val body : TContent? = null, 
    @SerialName("text") val text : TStr? = null, 
) : TContent
{
    override fun repr() : String = RT.structRepr("text", "font" to font, "fallback" to fallback, "style" to style, "weight" to weight, "stretch" to stretch, "size" to size, "fill" to fill, "stroke" to stroke, "tracking" to tracking, "spacing" to spacing, "cjk-latin-spacing" to cjkLatinSpacing, "baseline" to baseline, "overhang" to overhang, "top-edge" to topEdge, "bottom-edge" to bottomEdge, "lang" to lang, "region" to region, "script" to script, "dir" to dir, "hyphenate" to hyphenate, "kerning" to kerning, "alternates" to alternates, "stylistic-set" to stylisticSet, "ligatures" to ligatures, "discretionary-ligatures" to discretionaryLigatures, "historical-ligatures" to historicalLigatures, "number-type" to numberType, "number-width" to numberWidth, "slashed-zero" to slashedZero, "fractions" to fractions, "features" to features, null to body, null to text, )
}
