@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("text")
@Serializable
data class NGTText(
    @SerialName("font") val font : NGTArrayOrStr<TStr, >? = null, 
    @SerialName("fallback") val fallback : NGTBool? = null, 
    @SerialName("style") val style : NGTStr? = null, 
    @SerialName("weight") val weight : NGTIntOrStr? = null, 
    @SerialName("stretch") val stretch : NGTRatio? = null, 
    @SerialName("size") val size : NGTLength? = null, 
    @SerialName("fill") val fill : NGTColorOrGradientOrPattern? = null, 
    @SerialName("stroke") val stroke : NGTColorOrDictionaryOrGradientOrLengthOrNoneOrPatternOrStroke<*, >? = null, 
    @SerialName("tracking") val tracking : NGTLength? = null, 
    @SerialName("spacing") val spacing : NGTRelative? = null, 
    @SerialName("cjk-latin-spacing") val cjkLatinSpacing : NGTAutoOrNone? = null, 
    @SerialName("baseline") val baseline : NGTLength? = null, 
    @SerialName("overhang") val overhang : NGTBool? = null, 
    @SerialName("top-edge") val topEdge : NGTLengthOrStr? = null, 
    @SerialName("bottom-edge") val bottomEdge : NGTLengthOrStr? = null, 
    @SerialName("lang") val lang : NGTStr? = null, 
    @SerialName("region") val region : NGTNoneOrStr? = null, 
    @SerialName("script") val script : NGTAutoOrStr? = null, 
    @SerialName("dir") val dir : NGTAutoOrDirection? = null, 
    @SerialName("hyphenate") val hyphenate : NGTAutoOrBool? = null, 
    @SerialName("kerning") val kerning : NGTBool? = null, 
    @SerialName("alternates") val alternates : NGTBool? = null, 
    @SerialName("stylistic-set") val stylisticSet : NGTIntOrNone? = null, 
    @SerialName("ligatures") val ligatures : NGTBool? = null, 
    @SerialName("discretionary-ligatures") val discretionaryLigatures : NGTBool? = null, 
    @SerialName("historical-ligatures") val historicalLigatures : NGTBool? = null, 
    @SerialName("number-type") val numberType : NGTAutoOrStr? = null, 
    @SerialName("number-width") val numberWidth : NGTAutoOrStr? = null, 
    @SerialName("slashed-zero") val slashedZero : NGTBool? = null, 
    @SerialName("fractions") val fractions : NGTBool? = null, 
    @SerialName("features") val features : NGTArrayOrDictionary<TStr, TInt, >? = null, 
    @SerialName("body") val body : NGTContent? = null, 
    @SerialName("text") val text : NGTStr? = null, 
) : NGTContent
{
    override fun convert() = TText(font?.convert().cast(), fallback?.convert().cast(), style?.convert().cast(), weight?.convert().cast(), stretch?.convert().cast(), size?.convert().cast(), fill?.convert().cast(), stroke?.convert().cast(), tracking?.convert().cast(), spacing?.convert().cast(), cjkLatinSpacing?.convert().cast(), baseline?.convert().cast(), overhang?.convert().cast(), topEdge?.convert().cast(), bottomEdge?.convert().cast(), lang?.convert().cast(), region?.convert().cast(), script?.convert().cast(), dir?.convert().cast(), hyphenate?.convert().cast(), kerning?.convert().cast(), alternates?.convert().cast(), stylisticSet?.convert().cast(), ligatures?.convert().cast(), discretionaryLigatures?.convert().cast(), historicalLigatures?.convert().cast(), numberType?.convert().cast(), numberWidth?.convert().cast(), slashedZero?.convert().cast(), fractions?.convert().cast(), features?.convert().cast(), body?.convert().cast(), text?.convert().cast())
}
