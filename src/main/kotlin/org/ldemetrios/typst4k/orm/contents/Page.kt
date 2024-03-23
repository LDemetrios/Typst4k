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
    override fun repr() : String = RT.structRepr("page", Triple(false, "paper", paper), Triple(false, "width", width), Triple(false, "height", height), Triple(false, "flipped", flipped), Triple(false, "margin", margin), Triple(false, "binding", binding), Triple(false, "columns", columns), Triple(false, "fill", fill), Triple(false, "numbering", numbering), Triple(false, "number-align", numberAlign), Triple(false, "header", header), Triple(false, "header-ascent", headerAscent), Triple(false, "footer", footer), Triple(false, "footer-descent", footerDescent), Triple(false, "background", background), Triple(false, "foreground", foreground), Triple(false, null, body), )
}
