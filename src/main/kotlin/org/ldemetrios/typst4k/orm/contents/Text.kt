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
    override fun repr() : String = RT.structRepr("text", Triple(false, "font", font), Triple(false, "fallback", fallback), Triple(false, "style", style), Triple(false, "weight", weight), Triple(false, "stretch", stretch), Triple(false, "size", size), Triple(false, "fill", fill), Triple(false, "stroke", stroke), Triple(false, "tracking", tracking), Triple(false, "spacing", spacing), Triple(false, "cjk-latin-spacing", cjkLatinSpacing), Triple(false, "baseline", baseline), Triple(false, "overhang", overhang), Triple(false, "top-edge", topEdge), Triple(false, "bottom-edge", bottomEdge), Triple(false, "lang", lang), Triple(false, "region", region), Triple(false, "script", script), Triple(false, "dir", dir), Triple(false, "hyphenate", hyphenate), Triple(false, "kerning", kerning), Triple(false, "alternates", alternates), Triple(false, "stylistic-set", stylisticSet), Triple(false, "ligatures", ligatures), Triple(false, "discretionary-ligatures", discretionaryLigatures), Triple(false, "historical-ligatures", historicalLigatures), Triple(false, "number-type", numberType), Triple(false, "number-width", numberWidth), Triple(false, "slashed-zero", slashedZero), Triple(false, "fractions", fractions), Triple(false, "features", features), Triple(false, null, body), Triple(false, null, text), )
}
