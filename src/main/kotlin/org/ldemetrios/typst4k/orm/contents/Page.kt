package org.ldemetrios.typst4k.orm

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast

@SerialName("page")
@Serializable
data class TPage(
    @SerialName("paper") val paper : TStr? = null, 
    @SerialName("width") val width : TAutoOrLength? = null, 
    @SerialName("height") val height : TAutoOrLength? = null, 
    @SerialName("flipped") val flipped : TBool? = null, 
    @SerialName("margin") val margin : TAutoOrDictionaryOrRelative<*, >? = null, 
    @SerialName("binding") val binding : TAlignmentOrAuto? = null, 
    @SerialName("columns") val columns : TInt? = null, 
    @SerialName("fill") val fill : TColorOrGradientOrNoneOrPattern? = null, 
    @SerialName("numbering") val numbering : TNoneOrStr? = null, 
    @SerialName("number-align") val numberAlign : TAlignment? = null, 
    @SerialName("header") val header : TContentOrNone? = null, 
    @SerialName("header-ascent") val headerAscent : TRelative? = null, 
    @SerialName("footer") val footer : TContentOrNone? = null, 
    @SerialName("footer-descent") val footerDescent : TRelative? = null, 
    @SerialName("background") val background : TContentOrNone? = null, 
    @SerialName("foreground") val foreground : TContentOrNone? = null, 
    @SerialName("body") val body : TContent, 
) : TContent
{
    override fun repr() : String = RT.structRepr("page", "paper" to paper, "width" to width, "height" to height, "flipped" to flipped, "margin" to margin, "binding" to binding, "columns" to columns, "fill" to fill, "numbering" to numbering, "number-align" to numberAlign, "header" to header, "header-ascent" to headerAscent, "footer" to footer, "footer-descent" to footerDescent, "background" to background, "foreground" to foreground, null to body, )
}
