/*
 * Copyright (C) 2021 Jared Rummler
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jaredrummler.html

import android.graphics.Typeface
import android.text.Html
import android.text.Layout
import android.text.style.*
import android.widget.TextView
import androidx.annotation.ColorInt
import com.jaredrummler.html.Attribute.Align

// region a

/**
 * The <a> tag defines a hyperlink
 *
 * @param href indicates the link's destination.
 * @param target Specifies where to open the linked document.
 * @param text The text set as the element's [HtmlText].
 */
@HtmlTagMarker
class A(
    href: String? = null, target: Target? = null, text: HtmlText = ""
) : InlineTextElement("a", text) {

    /** Specifies the URL of the page the link goes to */
    var href: String
        get() = requireNotNull(attributes["href"])
        set(value) {
            attr("href", value)
        }

    /** Specifies where to open the linked document */
    var target: String?
        get() = attributes["target"]
        set(value) {
            attr("target", value)
        }

    init {
        attr("href", href)
        attr("target", target)
    }

    /**
     * The target attribute specifies where to open the linked document.
     *
     * Valid values: "_blank", "_parent", "_self", "_top"
     */
    enum class Target(override val value: String) : Attribute.Value {
        /** Opens the linked document in the same frame as it was clicked (this is default) */
        SELF("_self"),

        /** Opens the linked document in a new window or tab */
        BLANK("_blank"),

        /** Opens the linked document in the parent frame */
        PARENT("_parent"),

        /** Opens the linked document in the full body of the window */
        TOP("_top");
    }
}

// endregion

// region b

/**
 * The <b> tag specifies bold text without any extra importance.
 *
 * @param text The text set as the element's [HtmlText].
 */
@HtmlTagMarker
class B(text: HtmlText = "") : InlineTextElement("b", text)

/**
 * The Bigger Text element.
 *
 * The <big> HTML element renders the enclosed text uniformly—via [RelativeSizeSpan]—scaling the
 * size of the text to which it's attached by a factor of 1.25.
 *
 * @see RelativeSizeSpan
 * @see Html
 */
@HtmlTagMarker
class Big(text: HtmlText = "") : InlineTextElement("big", text)

/**
 * The <blockquote> HTML element sets a [QuoteSpan] on the rendered text passed in to
 * [Html.fromHtml], adding a vertical stripe to the text.
 *
 * @param text The text set as the element's [HtmlText].
 * @see QuoteSpan
 * @see Attribute.Style.strikethrough
 * @see Del
 * @see S
 */
@HtmlTagMarker
class BlockQuote(text: HtmlText = "") : TextElement("blockquote", text)

/**
 * The <br> HTML element adds a new line character ('\n') to the text.
 *
 * Note: Do not use [BR] to create margins between paragraphs; wrap them in [P] elements.
 */
@HtmlTagMarker
class BR : InlineTextElement("br")

// endregion

// region c

/**
 * The <cite> HTML element adds a [StyleSpan] with the [Typeface.ITALIC] style to the rendered text.
 *
 * @param text The text set as the element's [HtmlText].
 *
 * @see I
 * @see EM
 * @see Dfn
 */
@HtmlTagMarker
class Cite(text: HtmlText = "") : InlineTextElement("cite", text)

// endregion

// region d

/**
 * The <del> HTML elements adds a [StrikethroughSpan] on the text.
 *
 * @see QuoteSpan
 * @see Attribute.Style.strikethrough
 * @see BlockQuote
 * @see Strike
 */
@HtmlTagMarker
class Del(text: HtmlText = "") : DemarcatingElement("del", text)

/**
 * The <dfn> HTML element adds a [StyleSpan] with the [Typeface.ITALIC] style to the rendered text.
 *
 * @param text The text set as the element's [HtmlText].
 *
 * @see I
 * @see EM
 * @see Cite
 */
@HtmlTagMarker
class Dfn(text: HtmlText = "") : InlineTextElement("dfn", text)

/**
 * The <div> HTML element adds a new line after the inner HTML inside the <div> element,
 * separating it from other text/elements. Also, sets the [alignment][Layout.Alignment]
 * from the 'text-align' [style][Attribute.Style] attribute.
 *
 * @param align One of the following:
 *   [Align.CENTER], [Align.LEFT], [Align.RIGHT], or null for normal alignment.
 */
@HtmlTagMarker
class Div(align: Align? = null) : TextElement("div") {

    /**
     * The alignment of the div. Either "center", "left", "right" or null.
     *
     * @see Align
     */
    var align: String?
        get() = attributes["align"]
        set(value) {
            attr("align", value)
        }

    init {
        attr("align", align?.value)
    }
}

// endregion d

// region e

/**
 * The <em> (The Emphasis element) HTML element adds a [StyleSpan] with the [Typeface.ITALIC]
 * style to the rendered text.
 *
 * @param text The text set as the element's [HtmlText].
 *
 * @see I
 * @see Dfn
 * @see Cite
 */
@HtmlTagMarker
class EM(text: HtmlText = "") : InlineTextElement("em", text)

// endregion

// region f

/**
 * The <font> HTML element defines the font size, color and face for its content.
 *
 * @param face Sets a [TypefaceSpan] on the [text] using the [face] as the font family name.
 * @param color Sets a [ForegroundColorSpan] on the [text].
 * @param text The text set as the element's [HtmlText].
 */
class Font(
    face: String? = null,
    color: String? = null,
    text: HtmlText = ""
) : TextElement("font", text) {

    /**
     * The font family for this typeface.
     *
     * Examples include "monospace", "serif", and "sans-serif"
     */
    var face: String?
        get() = attributes["face"]
        set(value) {
            attr("face", value)
        }

    /**
     * The HEX color code or color name for the [text].
     */
    var color: String?
        get() = attributes["color"]
        set(value) {
            attr("color", value)
        }

    init {
        attr("face", face)
        attr("color", color)
    }

    /**
     * Set the color for the text within the [Font] element.
     *
     * @param color The color integer that defines the text color
     */
    fun color(@ColorInt color: Int) = apply {
        this.color = color.hex
    }

    companion object {
        private val Int?.hex: String?
            get() = if (this != null) '#' + String.format("%06X", 0xFFFFFF and this) else null
    }
}

// endregion

// region h

/**
 * Sets the text within the <h1> HTML element to have a [RelativeSizeSpan]
 * which scales the text by 150% with a [StyleSpan] with [Typeface.BOLD].
 *
 * @param text The text set as the element's [HtmlText].
 */
class H1(text: HtmlText = "") : ContentElement("h1", text)

/**
 * Sets the text within the <h2> HTML element to have a [RelativeSizeSpan]
 * which scales the text by 140% with a [StyleSpan] with [Typeface.BOLD].
 *
 * @param text The text set as the element's [HtmlText].
 */
class H2(text: HtmlText = "") : ContentElement("h2", text)

/**
 * Sets the text within the <h3> HTML element to have a [RelativeSizeSpan]
 * which scales the text by 130% with a [StyleSpan] with [Typeface.BOLD].
 *
 * @param text The text set as the element's [HtmlText].
 */
class H3(text: HtmlText = "") : ContentElement("h3", text)

/**
 * Sets the text within the <h4> HTML element to have a [RelativeSizeSpan]
 * which scales the text by 120% with a [StyleSpan] with [Typeface.BOLD].
 *
 * @param text The text set as the element's [HtmlText].
 */
class H4(text: HtmlText = "") : ContentElement("h4", text)

/**
 * Sets the text within the <h5> HTML element to have a [RelativeSizeSpan]
 * which scales the text by 110% with a [StyleSpan] with [Typeface.BOLD].
 *
 * @param text The text set as the element's [HtmlText].
 */
class H5(text: HtmlText = "") : ContentElement("h5", text)

/**
 * Sets the text within the <h4> HTML element to have a [StyleSpan] with [Typeface.BOLD].
 *
 * @param text The text set as the element's [HtmlText].
 */
class H6(text: HtmlText = "") : ContentElement("h6", text)

// endregion

// region i

/**
 * The <i> HTML element adds a [StyleSpan] with the [Typeface.ITALIC] style to the rendered text.
 *
 * @param text The text set as the element's [HtmlText].
 *
 * @see EM
 * @see Dfn
 * @see Cite
 */
class I(text: HtmlText = "") : InlineTextElement("i", text)

/**
 * The <img> HTML element embeds an image into the [TextView]. The image cannot be loaded unless an
 * [Html.ImageGetter] is passed to [Html.fromHtml].
 *
 * The image is obtained via [Html.ImageGetter.getDrawable] with the [src] set as the drawable path.
 *
 * The image is a Drawable representation of the image or `null` for a generic replacement image.
 * Make sure you call setBounds() on your Drawable if it doesn't already have its bounds set.
 *
 * @param src The image to load via [Html.ImageGetter.getDrawable].
 */
class Img(src: String? = null) : ImageElement("img") {

    /**
     * The image to load via [Html.ImageGetter.getDrawable].
     */
    var src: String?
        get() = attributes["src"]
        set(value) {
            attr("src", value)
        }

    init {
        attr("src", src)
    }
}

// endregion

// region l

/**
 * The <li> HTML element sets a [BulletSpan] (A span which styles paragraphs as bullet points)
 * on the [text].
 *
 * @param text The text set as the element's [HtmlText].
 */
class LI(text: HtmlText = "") : TextElement("li", text)

// endregion

// region p

/**
 * The <p> HTML element sets the [ParagraphStyle] on its text, respecting the
 * elements [Attribute.Style.align] value, [alignment][Layout.Alignment] via 'text-align',
 * [background color][BackgroundColorSpan], and [foreground color][ForegroundColorSpan].
 *
 * @param text The text set as the element's [HtmlText].
 */
class P(text: HtmlText = "") : TextElement("p", text)

// endregion

// region s

/**
 * The <s> HTML element sets a [QuoteSpan] on the rendered text passed in to [Html.fromHtml],
 * adding a vertical stripe to the text.
 *
 * @param text The text set as the element's [HtmlText].
 * @see QuoteSpan
 * @see Attribute.Style.strikethrough
 * @see Del
 * @see Strike
 */
class S(text: HtmlText = "") : InlineTextElement("s", text)

/**
 * The Small Text element.
 *
 * The <small> HTML element renders the enclosed text uniformly—via [RelativeSizeSpan]—scaling the
 * size of the text to which it's attached by a factor of 0.8.
 *
 * @see RelativeSizeSpan
 * @see Html
 */
class Small(text: HtmlText = "") : InlineTextElement("small", text)

/**
 * The <span> HTML element sets a respects the element's 'style' attribute, setting the
 * [alignment][Layout.Alignment] via 'text-align', [background color][BackgroundColorSpan],
 * and [foreground color][ForegroundColorSpan].
 *
 * @param text The text set as the element's [HtmlText].
 */
class Span : HtmlElement("span")

/**
 * The <strike> HTML element sets a [QuoteSpan] on the rendered text passed in to [Html.fromHtml],
 * adding a vertical stripe to the text.
 *
 * @param text The text set as the element's [HtmlText].
 * @see QuoteSpan
 * @see Attribute.Style.strikethrough
 * @see Del
 * @see S
 */
class Strike(text: HtmlText = "") : TextElement("strike", text)

/**
 * Sets the text within the <strong> HTML element to have a [StyleSpan] with [Typeface.BOLD].
 *
 * @param text The text set as the element's [HtmlText].
 */
class Strong(text: HtmlText = "") : InlineTextElement("strong", text)

/**
 * Sets a [SubscriptSpan] (span that moves the position of the text baseline lower) on the text.
 *
 * @param text The text set as the element's [HtmlText].
 */
class Sub(text: HtmlText = "") : InlineTextElement("sub", text)

/**
 * Sets a [SuperscriptSpan] (span that moves the position of the text baseline higher) on the text.
 *
 * @param text The text set as the element's [HtmlText].
 */
class Sup(text: HtmlText = "") : InlineTextElement("sup", text)

// endregion

// region t

/**
 * Sets a [TypefaceSpan] with the font family set to [Typeface.MONOSPACE] on the text.
 *
 * @param text The text set as the element's [HtmlText].
 */
class TT(text: HtmlText = "") : InlineTextElement("tt", text)

// endregion

// region u

/**
 * The <u> HTML element sets an [UnderlineSpan] on the text.
 *
 * @param text The text set as the element's [HtmlText].
 */
class U(text: HtmlText = "") : InlineTextElement("u", text)

/**
 * The <ul> HTML element represents an unordered list of [LI] items, rendered as a bulleted list.
 *
 * @param items The items for the unordered list.
 */
class UL(vararg items: LI) : HtmlElement("ul") {
    init {
        for (element in items) {
            add(element)
        }
    }
}

// endregion

/**
 * Use the HTML inline text semantic to define the meaning, structure, or style of a word, line,
 * or any arbitrary piece of text.
 */
abstract class InlineTextElement : TextElement {
    constructor(name: String) : super(name)
    constructor(name: String, text: HtmlText) : super(name, text)
}

/**
 * These elements let you provide indications that specific parts of the text have been altered.
 */
typealias DemarcatingElement = TextElement

/**
 * Content sectioning elements allow you to organize the document content into logical pieces.
 * Use the sectioning elements to create a broad outline for your page content.
 */
typealias ContentElement = TextElement

typealias ImageElement = HtmlElement
