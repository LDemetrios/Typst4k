@file:Suppress("UNNECESSARY_SAFE_CALL", "KotlinRedundantDiagnosticSuppress")

package org.ldemetrios.typst4k.orm.nongeneric

import kotlinx.serialization.*
import org.ldemetrios.typst4k.rt.*
import org.ldemetrios.utilities.cast
import org.ldemetrios.typst4k.orm.*


@SerialName("page")
@Serializable
data class NGTPage(
    @SerialName("paper") val paper : NGTStr? = null, 
    @SerialName("width") val width : NGTAutoOrLength? = null, 
    @SerialName("height") val height : NGTAutoOrLength? = null, 
    @SerialName("flipped") val flipped : NGTBool? = null, 
    @SerialName("margin") val margin : NGTAutoOrDictionaryOrRelative<*, >? = null, 
    @SerialName("binding") val binding : NGTAlignmentOrAuto? = null, 
    @SerialName("columns") val columns : NGTInt? = null, 
    @SerialName("fill") val fill : NGTColorOrGradientOrNoneOrPattern? = null, 
    @SerialName("numbering") val numbering : NGTNoneOrStr? = null, 
    @SerialName("number-align") val numberAlign : NGTAlignment? = null, 
    @SerialName("header") val header : NGTContentOrNone? = null, 
    @SerialName("header-ascent") val headerAscent : NGTRelative? = null, 
    @SerialName("footer") val footer : NGTContentOrNone? = null, 
    @SerialName("footer-descent") val footerDescent : NGTRelative? = null, 
    @SerialName("background") val background : NGTContentOrNone? = null, 
    @SerialName("foreground") val foreground : NGTContentOrNone? = null, 
    @SerialName("body") val body : NGTContent, 
) : NGTContent
{
    override fun convert() = TPage(paper?.convert().cast(), width?.convert().cast(), height?.convert().cast(), flipped?.convert().cast(), margin?.convert().cast(), binding?.convert().cast(), columns?.convert().cast(), fill?.convert().cast(), numbering?.convert().cast(), numberAlign?.convert().cast(), header?.convert().cast(), headerAscent?.convert().cast(), footer?.convert().cast(), footerDescent?.convert().cast(), background?.convert().cast(), foreground?.convert().cast(), body?.convert().cast())
}
