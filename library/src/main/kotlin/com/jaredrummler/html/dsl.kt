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
import androidx.core.text.HtmlCompat
import com.jaredrummler.html.Attribute.Align

/**
 * DSL to create valid HTML to be rendered by [Html.fromHtml] and set on a [TextView].
 *
 * **Example usage:**
 *
 * ```kotlin
 * com.jaredrummler.html {
 *   h5("Social Media Links")
 *   div {
 *     text = ul {
 *       li {
 *         font {
 *           color = "#1da1f2"
 *           a {
 *             href = "https://twitter.com/jaredrummler"
 *             text = strong("Twitter")
 *           }
 *         }
 *       }
 *       li {
 *         font {
 *           color = "#333"
 *           a {
 *             href = "https://github.com/jaredrummler"
 *             text = strong("Github")
 *           }
 *         }
 *       }
 *     }
 *   }
 * }
 * ```
 */
fun html(init: HTML.() -> Unit): HTML = HTML.create(init)

/**
 * Set HTML on a [TextView].
 *
 * @param flags The default flag is [HtmlCompat.FROM_HTML_MODE_COMPACT]. Valid HTML flags:
 *   [Html.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH]
 *   [Html.FROM_HTML_SEPARATOR_LINE_BREAK_HEADING]
 *   [Html.FROM_HTML_SEPARATOR_LINE_BREAK_LIST_ITEM]
 *   [Html.FROM_HTML_SEPARATOR_LINE_BREAK_LIST]
 *   [Html.FROM_HTML_SEPARATOR_LINE_BREAK_DIV]
 *   [Html.FROM_HTML_SEPARATOR_LINE_BREAK_BLOCKQUOTE]
 *   [Html.FROM_HTML_OPTION_USE_CSS_COLORS]
 *   [Html.FROM_HTML_MODE_COMPACT]
 *   [Html.FROM_HTML_MODE_LEGACY]
 * @param init DSL to create valid HTML to be rendered by [Html.fromHtml] and set on the [TextView].
 */
fun TextView.setHtml(flags: Int = HtmlCompat.FROM_HTML_MODE_COMPACT, init: HTML.() -> Unit) {
    val source = HTML().apply(init).render()
    text = HtmlCompat.fromHtml(source, flags)
}

// region a

/**
 * Function for the HTML DSL which creates an [Anchor][A] element.
 * The element is added to the parent node as its child.
 *
 * ```kotlin
 * a {
 *   href = "https://google.com"
 *   text = "Google"
 * }
 * ```
 *
 * or, using parameters which are set to null by default:
 *
 * ```kotlin
 * a(href = "https://google.com", text = "Google")
 * ```
 *
 * Calling [A.html] would create the following string:
 *
 *     <a href="https://google.com">Google</a>
 *
 * @param href Specifies the URL of the page the link goes to.
 * @param target Specifies where to open the link.
 * @param text Sets the text for the link.
 * @param block Apply lambda on the new instance of [A].
 * @return The new instance of the [A] element with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.a(
    href: String? = null,
    target: A.Target? = null,
    text: HtmlText = "",
    crossinline block: A.() -> Unit = {}
): A = A(href, target, text).apply(block).apply(::init)

// endregion

// region b

/**
 * Function for the HTML DSL which creates a [b][B] element. The element is added as a child to the
 * parent node [T]. The [b][B] tag wraps the [text] in a [StyleSpan]  with [Typeface.BOLD].
 *
 * ```kotlin
 * b {
 *   text = "This text is bold"
 * }
 * ```
 *
 * or, using parameters which are set to null by default:
 *
 * ```kotlin
 * b(text = "This text is bold")
 * ```
 *
 * Calling [B.html] would return the following string:
 *
 *     <b>This text is bold</b>
 *
 * @param text Sets the text on the element.
 * @param block Apply lambda on the new instance of [B].
 * @return The new instance of [B] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.b(
    text: HtmlText = "",
    crossinline block: B.() -> Unit = {}
): B = B(text).apply(block).apply(::init)

/**
 * Function for the HTML DSL which creates a [big][Big] element.
 *
 * The <big> HTML element renders the enclosed text uniformly—via [RelativeSizeSpan]—scaling the
 * size of the text to which it's attached by a factor of 1.25.
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * big {
 *   text = "That's what she said"
 * }
 * ```
 *
 * or, using the [text] parameter which is set to null by default:
 *
 * ```kotlin
 * big(text = "That's what she said")
 * ```
 *
 * Calling [Big.html] would return the following string:
 *
 *     <big>That's what she said</big>
 *
 * @param text Sets the text on the element.
 * @param block Apply lambda on the new instance of [Big].
 * @return The new instance of [Big] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.big(
    text: HtmlText = "",
    crossinline block: Big.() -> Unit = {}
): Big = Big(text).apply(block).apply(::init)

/**
 * Function for the HTML DSL which creates a [blockquote][BlockQuote] element.
 *
 * The <blockquote> HTML element sets a [QuoteSpan] on the rendered text passed in to
 * [Html.fromHtml], adding a vertical stripe to the text.
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * blockquote {
 *   text = "That's what she said"
 * }
 * ```
 *
 * or, using the [text] parameter which is set to null by default:
 *
 * ```kotlin
 * blockquote(text = "That's what she said")
 * ```
 *
 * Calling [BlockQuote.html] would return the following string:
 *
 *     <blockquote>That's what she said</blockquote>
 *
 * @param text Sets the text on the element.
 * @param block Apply lambda on the new instance of [BlockQuote].
 * @return The new instance of [BlockQuote] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.blockquote(
    text: HtmlText = "",
    crossinline block: BlockQuote.() -> Unit = {}
): BlockQuote = BlockQuote(text).apply(block).apply(::init)

/**
 * Function for the HTML DSL which creates a [br][BR] element.
 *
 * The <br> HTML element adds a new line character (`'\n'`) to the text.
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * br()
 * ```
 *
 * Calling [BR.html] would return the following string:
 *
 *     <br>
 *
 * @param text Sets the text on the element.
 * @param block Apply lambda on the new instance of [BlockQuote].
 * @return The new instance of [BlockQuote] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.br(
    crossinline block: BR.() -> Unit = {}
): BR = BR().apply(block).apply(::init)

// endregion

// region c

/**
 * Function for the HTML DSL which creates a [cite][Cite] element.
 *
 * The <cite> HTML element adds a [StyleSpan] with the [Typeface.ITALIC] style to the rendered text.
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * cite {
 *   text = "Italic text using HTML cite tag"
 * }
 * ```
 *
 * or, using the [text] parameter which is set to null by default:
 *
 * ```kotlin
 * cite(text = "Italic text using HTML cite tag")
 * ```
 *
 * Calling [Cite.html] would return the following string:
 *
 *     <cite>Italic text using HTML cite tag</cite>
 *
 * @param text Sets the text on the element.
 * @param block Apply lambda on the new instance of [Cite].
 * @return The new instance of [Cite] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.cite(
    text: HtmlText = "",
    crossinline block: Cite.() -> Unit = {}
): Cite = Cite(text).apply(block).apply(::init)

// endregion

// region d

/**
 * Function for the HTML DSL which creates a [del][Del] element.
 *
 * The <del> HTML elements adds a [StrikethroughSpan] on the inner HTML elements.
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * del {
 *   p {
 *     text = "This paragraph has been deleted."
 *   }
 * }
 * ```
 *
 * Calling [Del.html] would return the following string:
 *
 *     <del><p>This paragraph has been deleted.</p></del>
 *
 * @param block Apply lambda on the new instance of [Del].
 * @return The new instance of [Del] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.del(
    crossinline block: Del.() -> Unit = {}
): Del = Del().apply(block).apply(::init)

/**
 * Function for the HTML DSL which creates a [dfn][Dfn] element.
 *
 * The <dfn> HTML element adds a [StyleSpan] with the [Typeface.ITALIC] style to the rendered text.
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * dfn {
 *   text = "Italic text using HTML dfn tag"
 * }
 * ```
 *
 * or, using the [text] parameter which is set to null by default:
 *
 * ```kotlin
 * dfn(text = "Italic text using HTML dfn tag")
 * ```
 *
 * Calling [Dfn.html] would return the following string:
 *
 *     <cite>Italic text using HTML cite tag</cite>
 *
 * @param text Sets the text on the element.
 * @param block Apply lambda on the new instance of [Dfn].
 * @return The new instance of [Dfn] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.dfn(
    text: HtmlText = "",
    crossinline block: Dfn.() -> Unit = {}
): Dfn = Dfn(text).apply(block).apply(::init)

/**
 * Function for the HTML DSL which creates a [div][Div] element.
 *
 * The <div> HTML element adds a new line after the inner HTML inside the <div> element,
 * separating it from other text/elements. Also, sets the [alignment][Layout.Alignment]
 * from the 'text-align' [style][Attribute.Style] attribute.
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * div {
 *   p {
 *     text = "This paragraph is inside a div."
 *   }
 * }
 * ```
 *
 * Calling [Del.html] would return the following string:
 *
 *     <div><p>This paragraph is inside a div.</p></div>
 *
 * @param align One of the following:
 *   [Align.CENTER], [Align.LEFT], [Align.RIGHT], or null for normal alignment.
 * @param block Apply lambda on the new instance of [Div].
 * @return The new instance of [Div] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.div(
    align: Align? = null,
    crossinline block: Div.() -> Unit = {}
): Div = Div(align).apply(block).apply(::init)

// endregion

// region e

/**
 * Function for the HTML DSL which creates a [em][EM] element.
 *
 * The <em> (The Emphasis element) HTML element adds a [StyleSpan] with the [Typeface.ITALIC]
 * style to the rendered text.
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * em {
 *   text = "Italic text using HTML em tag"
 * }
 * ```
 *
 * or, using the [text] parameter which is set to null by default:
 *
 * ```kotlin
 * em(text = "Italic text using HTML em tag")
 * ```
 *
 * Calling [EM.html] would return the following string:
 *
 *     <em>Italic text using HTML em tag</em>
 *
 * @param text Sets the text on the element.
 * @param block Apply lambda on the new instance of [EM].
 * @return The new instance of [EM] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.em(
    text: HtmlText = "",
    crossinline block: EM.() -> Unit = {}
): EM = EM(text).apply(block).apply(::init)

// endregion

// region f

/**
 * Function for the HTML DSL which creates a [div][Div] element.
 *
 * The <font> HTML element defines the font size, color and face for its content.
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * font {
 *   face = "sans-serif-condensed-light" // RobotoCondensed-Light.ttf
 *   text = "Text with a custom font"
 *   color = "red"
 * }
 * ```
 *
 * or, using the [text], [face], and [color] parameters which are set to null by default:
 *
 * ```kotlin
 * font(text = "Text with a custom font", face = "sans-serif-condensed-light", color = "red")
 * ```
 *
 * Calling [Font.html] would return the following string:
 *
 *     <font face="sans-serif-condensed-light" color="red">Text with a custom font</font>
 *
 * @param face Sets a [TypefaceSpan] on the [text] using the [face] as the font family name.
 * @param color Sets a [ForegroundColorSpan] on the [text].
 * @param text The text set as the element's [HtmlText].
 * @param block Apply lambda on the new instance of [Div].
 * @return The new instance of [Font] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.font(
    face: String? = null,
    color: String? = null,
    text: HtmlText = "",
    crossinline block: Font.() -> Unit = {}
): Font = Font(face, color, text).apply(block).apply(::init)

// endregion

// region h

/**
 * Function for the HTML DSL which creates a [h1][H1] element.
 *
 * The <h1> HTML element sets the text to have a [RelativeSizeSpan] which scales the text by 150%
 * with a [StyleSpan] with [Typeface.BOLD].
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * h1 {
 *   text = "Heading 1"
 * }
 * ```
 *
 * or, using the [text] parameter which is set to null by default:
 *
 * ```kotlin
 * h1(text = "Header 1")
 * ```
 *
 * Calling [H1.html] would return the following string:
 *
 *     <h1>Heading 1</h1>
 *
 * @param text Sets the text on the element.
 * @param block Apply lambda on the new instance of [H1].
 * @return The new instance of [H1] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.h1(
    text: HtmlText = "",
    crossinline block: H1.() -> Unit = { }
): H1 = H1(text).apply(block).apply(::init)

/**
 * Function for the HTML DSL which creates a [h2][H2] element.
 *
 * The <h2> HTML element sets the text to have a [RelativeSizeSpan] which scales the text by 140%
 * with a [StyleSpan] with [Typeface.BOLD].
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * h2 {
 *   text = "Heading 2"
 * }
 * ```
 *
 * or, using the [text] parameter which is set to null by default:
 *
 * ```kotlin
 * h2(text = "Header 2")
 * ```
 *
 * Calling [H2.html] would return the following string:
 *
 *     <h2>Heading 2</h2>
 *
 * @param text Sets the text on the element.
 * @param block Apply lambda on the new instance of [H2].
 * @return The new instance of [H2] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.h2(
    text: HtmlText = "",
    crossinline block: H2.() -> Unit = { }
): H2 = H2(text).apply(block).apply(::init)

/**
 * Function for the HTML DSL which creates a [h3][H3] element.
 *
 * The <h3> HTML element sets the text to have a [RelativeSizeSpan] which scales the text by 130%
 * with a [StyleSpan] with [Typeface.BOLD].
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * h3 {
 *   text = "Heading 3"
 * }
 * ```
 *
 * or, using the [text] parameter which is set to null by default:
 *
 * ```kotlin
 * h3(text = "Header 3")
 * ```
 *
 * Calling [H3.html] would return the following string:
 *
 *     <h3>Heading 3</h3>
 *
 * @param text Sets the text on the element.
 * @param block Apply lambda on the new instance of [H3].
 * @return The new instance of [H3] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.h3(
    text: HtmlText = "",
    crossinline block: H3.() -> Unit = { }
): H3 = H3(text).apply(block).apply(::init)

/**
 * Function for the HTML DSL which creates a [h4][H4] element.
 *
 * The <h4> HTML element sets the text to have a [RelativeSizeSpan] which scales the text by 120%
 * with a [StyleSpan] with [Typeface.BOLD].
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * h4 {
 *   text = "Heading 4"
 * }
 * ```
 *
 * or, using the [text] parameter which is set to null by default:
 *
 * ```kotlin
 * h4(text = "Header 4")
 * ```
 *
 * Calling [H4.html] would return the following string:
 *
 *     <h4>Heading 4</h4>
 *
 * @param text Sets the text on the element.
 * @param block Apply lambda on the new instance of [H4].
 * @return The new instance of [H4] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.h4(
    text: HtmlText = "",
    crossinline block: H4.() -> Unit = { }
): H4 = H4(text).apply(block).apply(::init)

/**
 * Function for the HTML DSL which creates a [h5][H5] element.
 *
 * The <h5> HTML element sets the text to have a [RelativeSizeSpan] which scales the text by 110%
 * with a [StyleSpan] with [Typeface.BOLD].
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * h5 {
 *   text = "Heading 5"
 * }
 * ```
 *
 * or, using the [text] parameter which is set to null by default:
 *
 * ```kotlin
 * h5(text = "Header 5")
 * ```
 *
 * Calling [H5.html] would return the following string:
 *
 *     <h5>Heading 5</h5>
 *
 * @param text Sets the text on the element.
 * @param block Apply lambda on the new instance of [H5].
 * @return The new instance of [H5] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.h5(
    text: HtmlText = "",
    crossinline block: H5.() -> Unit = { }
): H5 = H5(text).apply(block).apply(::init)

/**
 * Function for the HTML DSL which creates a [h6][H6] element.
 *
 * The <h6> HTML element sets the text to have a [StyleSpan] with [Typeface.BOLD].
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * h6 {
 *   text = "Heading 6"
 * }
 * ```
 *
 * or, using the [text] parameter which is set to null by default:
 *
 * ```kotlin
 * h6(text = "Header 6")
 * ```
 *
 * Calling [H6.html] would return the following string:
 *
 *     <h6>Heading 6</h6>
 *
 * @param text Sets the text on the element.
 * @param block Apply lambda on the new instance of [H6].
 * @return The new instance of [H6] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.h6(
    text: HtmlText = "",
    crossinline block: H6.() -> Unit = { }
): H6 = H6(text).apply(block).apply(::init)

// endregion

// region i

/**
 * Function for the HTML DSL which creates an [i][I] element.
 *
 * The <i> HTML element adds a [StyleSpan] with the [Typeface.ITALIC] style to the rendered text.
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * i {
 *   text = "Italic text using HTML i tag"
 * }
 * ```
 *
 * or, using the [text] parameter which is set to null by default:
 *
 * ```kotlin
 * i(text = "Italic text using HTML i tag")
 * ```
 *
 * Calling [I.html] would return the following string:
 *
 *     <i>Italic text using HTML i tag</i>
 *
 * @param text Sets the text on the element.
 * @param block Apply lambda on the new instance of [I].
 * @return The new instance of [I] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.i(
    text: HtmlText = "",
    crossinline block: I.() -> Unit = { }
): I = I(text).apply(block).apply(::init)

/**
 * Function for the HTML DSL which creates an [img][Img] element.
 *
 * The <img> HTML element embeds an image into the [TextView]. The image cannot be loaded unless an
 * [Html.ImageGetter] is passed to [Html.fromHtml].
 *
 * The image is obtained via [Html.ImageGetter.getDrawable] with the [src] set as the drawable path.
 *
 * The image is a Drawable representation of the image or `null` for a generic replacement image.
 * Make sure you call setBounds() on your Drawable if it doesn't already have its bounds set.
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * img {
 *   src = "image.png"
 * }
 * ```
 *
 * or, using the [src] parameter which is set to null by default:
 *
 * ```kotlin
 * i(src = "image.png")
 * ```
 *
 * Calling [Img.html] would return the following string:
 *
 *     <img src="image.png">
 *
 * @param src The image to load via [Html.ImageGetter.getDrawable].
 * @param block Apply lambda on the new instance of [Img].
 * @return The new instance of [Img] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.img(
    src: String? = null,
    crossinline block: Img.() -> Unit = {}
): Img = Img(src).apply(block).apply(::init)

// endregion

// region l

/**
 * Function for the HTML DSL which creates an [li][LI] element.
 *
 * The <li> HTML element sets a [BulletSpan] (A span which styles paragraphs as bullet points)
 * on the [text].
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * ul {
 *   li {
 *     text = "List item text with a bullet"
 *   }
 * }
 * ```
 *
 * or, using the [text] parameter which is set to null by default:
 *
 * ```kotlin
 * ul {
 *   li(text = "List item text with a bullet")
 * }
 * ```
 *
 * Calling [LI.html] would return the following string:
 *
 *     <ul><li>List item text with a bullet</li></ul>
 *
 * @param text Sets the text on the element.
 * @param block Apply lambda on the new instance of [LI].
 * @return The new instance of [LI] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.li(
    text: HtmlText = "",
    crossinline block: LI.() -> Unit = {}
): LI = LI(text).apply(block).apply(::init)

// endregion

// region p

/**
 * Function for the HTML DSL which creates an [p][P] element.
 *
 * The <p> HTML element sets the [ParagraphStyle] on its text, respecting the
 * elements [Attribute.Style.align] value, [alignment][Layout.Alignment] via 'text-align',
 * [background color][BackgroundColorSpan], and [foreground color][ForegroundColorSpan].
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * com.jaredrummler.html {
 *   p {
 *     text = "This is the first paragraph of text."
 *   }
 *   p {
 *     text = "This is the second paragraph."
 *   }
 * }
 * ```
 *
 * or, using the [text] parameter which is set to null by default:
 *
 * ```kotlin
 * com.jaredrummler.html {
 *   p(text = "This is the first paragraph of text.")
 *   p(text = "This is the second paragraph.")
 * }
 * ```
 *
 * Calling [P.html] would return the following string:
 *
 *     <p>This is the first paragraph of text.</p><p>This is the second paragraph.</p>
 *
 * @param text Sets the text on the element.
 * @param block Apply lambda on the new instance of [P].
 * @return The new instance of [P] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.p(text: HtmlText = "", crossinline block: P.() -> Unit = {}): P =
    P(text).apply(block).apply(::init)

// endregion

// region s

/**
 * Function for the HTML DSL which creates an [s][S] element.
 *
 * The <s> HTML element sets a [QuoteSpan] on the rendered text passed in to [Html.fromHtml],
 * adding a vertical stripe to the text.
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * com.jaredrummler.html {
 *   s {
 *     text = "This text has a vertical line through it."
 *   }
 * }
 * ```
 *
 * or, using the [text] parameter which is set to null by default:
 *
 * ```kotlin
 * com.jaredrummler.html {
 *   s(text = "This text has a vertical line through it.")
 * }
 * ```
 *
 * Calling [S.html] would return the following string:
 *
 *     <s>This text has a vertical line through it.</s>
 *
 * @param text Sets the text on the element.
 * @param block Apply lambda on the new instance of [S].
 * @return The new instance of [S] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.s(
    text: String = "",
    crossinline block: S.() -> Unit = {}
): S = S(text).apply(block).apply(::init)

/**
 * Function for the HTML DSL which creates a [small][Small] element.
 *
 * The <small> HTML element renders the enclosed text uniformly—via [RelativeSizeSpan]—scaling the
 * size of the text to which it's attached by a factor of 0.8.
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * small {
 *   text = "This text is scaled down by 0.8"
 * }
 * ```
 *
 * or, using the [text] parameter which is set to null by default:
 *
 * ```kotlin
 * small(text = "This text is scaled down by 0.8")
 * ```
 *
 * Calling [Small.html] would return the following string:
 *
 *     <small>This text is scaled down by 0.8</small>
 *
 * @param text Sets the text on the element.
 * @param block Apply lambda on the new instance of [Small].
 * @return The new instance of [Small] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.small(
    text: HtmlText = "",
    crossinline block: Small.() -> Unit = {}
): Small = Small(text).apply(block).apply(::init)

/**
 * Function for the HTML DSL which creates a [span][Span] element.
 *
 * The <span> HTML element sets a respects the element's 'style' attribute, setting the
 * [alignment][Layout.Alignment] via 'text-align', [background color][BackgroundColorSpan],
 * and [foreground color][ForegroundColorSpan].
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * com.jaredrummler.html {
 *   span {
 *     style.background(Color.GRAY)
 *     style.align(Align.CENTER)
 *     style.color(Color.BLUE)
 *   }
 * }
 * ```
 *
 * Calling [Span.html] would return the following string:
 *
 *     <span style="background-color=#888888;align=center;color=#0000FF;"></span>
 *
 * @param block Apply lambda on the new instance of [Small].
 * @return The new instance of [Small] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.span(
    crossinline block: Span.() -> Unit = {}
): Span = Span().apply(block).apply(::init)


/**
 * Function for the HTML DSL which creates an [strike][Strike] element.
 *
 * The <s> HTML element sets a [QuoteSpan] on the rendered text passed in to [Html.fromHtml],
 * adding a vertical stripe to the text.
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * com.jaredrummler.html {
 *   strike {
 *     text = "This text has a vertical line through it."
 *   }
 * }
 * ```
 *
 * or, using the [text] parameter which is set to null by default:
 *
 * ```kotlin
 * com.jaredrummler.html {
 *   s(text = "This text has a vertical line through it.")
 * }
 * ```
 *
 * Calling [Strike.html] would return the following string:
 *
 *     <strike>This text has a vertical line through it.</strike>
 *
 * @param text Sets the text on the element.
 * @param block Apply lambda on the new instance of [Strike].
 * @return The new instance of [Strike] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.strike(
    text: HtmlText = "",
    crossinline block: Strike.() -> Unit = {}
): Strike = Strike(text).apply(block).apply(::init)

/**
 * Function for the HTML DSL which creates a [strong][Strong] element.
 *
 * Sets the text within the <strong> HTML element to have a [StyleSpan] with [Typeface.BOLD].
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * strong {
 *   text = "This text is bold"
 * }
 * ```
 *
 * or, using the [text] parameter which is set to null by default:
 *
 * ```kotlin
 * strong(text = "This text is bold")
 * ```
 *
 * Calling [Strong.html] would return the following string:
 *
 *     <strong>This text is bold</strong>
 *
 * @param text Sets the text on the element.
 * @param block Apply lambda on the new instance of [Strong].
 * @return The new instance of [Strong] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.strong(
    text: HtmlText = "",
    crossinline block: Strong.() -> Unit = {}
): Strong = Strong(text).apply(block).apply(::init)

/**
 * Function for the HTML DSL which creates a [sub][Sub] element.
 *
 * Sets a [SubscriptSpan] (span that moves the position of the text baseline lower) on the text.
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * sub {
 *   text = "This text is lower"
 * }
 * ```
 *
 * or, using the [text] parameter which is set to null by default:
 *
 * ```kotlin
 * sub(text = "This text is lower")
 * ```
 *
 * Calling [Sub.html] would return the following string:
 *
 *     <sub>This text is lower</sub>
 *
 * @param text Sets the text on the element.
 * @param block Apply lambda on the new instance of [Sub].
 * @return The new instance of [Sub] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.sub(
    text: HtmlText = "",
    crossinline block: Sub.() -> Unit = {}
): Sub = Sub(text).apply(block).apply(::init)

/**
 * Function for the HTML DSL which creates a [sup][Sup] element.
 *
 * Sets a [SubscriptSpan] (span that moves the position of the text baseline lower) on the text.
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * sup {
 *   text = "This text is higher"
 * }
 * ```
 *
 * or, using the [text] parameter which is set to null by default:
 *
 * ```kotlin
 * sup(text = "This text is higher")
 * ```
 *
 * Calling [Sup.html] would return the following string:
 *
 *     <sup>This text is higher</sup>
 *
 * @param text Sets the text on the element.
 * @param block Apply lambda on the new instance of [Sup].
 * @return The new instance of [Sup] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.sup(
    text: HtmlText = "",
    crossinline block: Sup.() -> Unit = {}
): Sup = Sup(text).apply(block).apply(::init)

// endregion

// region t

/**
 * Function for the HTML DSL which creates a [tt][TT] element.
 *
 * Sets a [TypefaceSpan] with the font family set to [Typeface.MONOSPACE] on the text.
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * tt {
 *   text = "Text in monospace typeface"
 * }
 * ```
 *
 * or, using the [text] parameter which is set to null by default:
 *
 * ```kotlin
 * tt(text = "Text in monospace typeface")
 * ```
 *
 * Calling [TT.html] would return the following string:
 *
 *     <tt>Text in monospace typeface</tt>
 *
 * @param text Sets the text on the element.
 * @param block Apply lambda on the new instance of [TT].
 * @return The new instance of [TT] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.tt(
    text: HtmlText = "",
    crossinline block: TT.() -> Unit = {}
): TT = TT(text).apply(block).apply(::init)

/// endregion

// region u

/**
 * Function for the HTML DSL which creates a [u][U] element.
 *
 * The <u> HTML element sets an [UnderlineSpan] on the text.
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * u {
 *   text = "This text has an underline."
 * }
 * ```
 *
 * or, using the [text] parameter which is set to null by default:
 *
 * ```kotlin
 * u(text = "This text has an underline.")
 * ```
 *
 * Calling [U.html] would return the following string:
 *
 *     <u>This text has an underline.</u>
 *
 * @param text Sets the text on the element.
 * @param block Apply lambda on the new instance of [U].
 * @return The new instance of [U] with any arguments set as text or tag attributes.
 */
@HtmlTagMarker
inline fun <reified T : Node> T.u(
    text: HtmlText = "",
    crossinline block: U.() -> Unit = {}
): U = U(text).apply(block).apply(::init)

/**
 * Function for the HTML DSL which creates an [ul][LI] element.
 *
 * The <ul> HTML element sets a [BulletSpan] (A span which styles paragraphs as bullet points)
 * on every [LI] child.
 *
 * The element is added as a child to the parent node [T].
 *
 * ```kotlin
 * ul {
 *   li {
 *     text = "Item 1"
 *   }
 *   li {
 *     text = "Item 2"
 *   }
 * }
 * ```
 *
 * Calling [UL.html] would return the following string:
 *
 *     <ul><li>Item 1</li><li>Item 2</li></ul>
 *
 * @param items The items for the unordered list.
 * @param block Apply lambda on the new instance of [UL].
 * @return The new instance of [UL] with any arguments set as text or tag attributes.
 * @see li
 */
@HtmlTagMarker
inline fun <reified T : Node> T.ul(
    vararg items: LI,
    crossinline block: UL.() -> Unit = {}
): UL = UL(*items).apply(block).apply(::init)

// endregion

/**
 * Initializes the [element] by setting the parent node as the receiver [N], unless the parent
 * is the root node. Then, the element is added to the parent as a child element.
 *
 * @param element The element to initialize and add to its parent.
 */
fun <E : HtmlElement, N : Node> N.init(element: E) = apply {
    if (this is HtmlElement && this::class != HTML::class) {
        element.parent = this
    }
    add(element)
}

/**
 * Used in [TextElement] and can be set as a [String], as another [HtmlElement] which is rendered
 * when converting to com.jaredrummler.html via [HtmlRenderer], a number. Other types render to [Any.toString].
 */
typealias HtmlText = Any?
