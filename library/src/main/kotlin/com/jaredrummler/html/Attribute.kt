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

import android.text.Html
import android.text.Layout
import android.text.style.*
import android.widget.TextView

/**
 * HTML Attribute. A name/value pair.
 *
 * Elements in HTML have attributes; these are additional values that configure the elements or
 * adjust their behavior in various ways to meet the criteria the users want.
 *
 * @property key The name of the attribute.
 * @property value A string value that configures the elements or adjust their behavior.
 * @author Jared Rummler (jaredrummler@gmail.com)
 * @since July 6, 2021
 */
data class Attribute(val key: String, val value: String) {

    override fun toString(): String = "$key=\"$value\""

    /**
     * Interface definition used for any [Enum] that maps to an attribute's value.
     *
     * @see Attribute.value
     */
    interface Value {

        /** The value of an attribute */
        val value: String
    }

    /**
     * The css attribute that [TextView] uses via [Html.fromHtml] to set a
     * [ParagraphStyle]/[AlignmentSpan] on the text in the HTML node, defining
     * the alignment of text at the paragraph level.
     *
     * @see Attribute.Value
     */
    enum class Align(override val value: String) : Value {
        /** @see Layout.Alignment.ALIGN_CENTER */
        CENTER("center"),

        /** @see Layout.Alignment.ALIGN_OPPOSITE */
        RIGHT("right"),

        /** @see Layout.Alignment.ALIGN_NORMAL */
        LEFT("left");
    }

    /**
     * The css attribute that [TextView] uses via [Html.fromHtml] to determine if the text
     * is set to display right-to-left or left-to-right.
     *
     * This is used in the 'dir' global attribute of an element which indicates
     * the directionality of the element's text.
     *
     * **See Also:** [dir][https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/dir]
     * @see Attribute.Value
     */
    enum class Direction(override val value: String) : Value {
        /** Left-To-Right */
        RTL("rtl"),

        /** Right-to-left */
        LTR("ltr");
    }

    /**
     * Class defined for creating, adding and modifying the global 'style' CSS attribute.
     *
     * The 'style' attribute is used by [Html] to set the [alignment][Layout.Alignment],
     * [background color][BackgroundColorSpan], [strikethrough][StrikethroughSpan], and
     * [foreground color][ForegroundColorSpan].
     *
     * @param element The HTML Element to set the 'style' attribute on.
     */
    class Style(private val element: HtmlElement) : Renderer<String> {

        /**
         * Set a style attribute on the 'style' attribute of the [element].
         *
         * @param key The CSS attribute name.
         * @param value The CSS attribute value.
         */
        operator fun set(key: String, value: String) {
            val style = element.attributes[STYLE] ?: ""
            element.attributes.remove(STYLE)
            element.attributes[STYLE] = "$style$key:$value;"
        }

        /**
         * Get the style value with the given name.
         *
         * @param name The CSS style attribute name.
         * @return The value of the attribute or null if it has not been set.
         */
        operator fun get(name: String): String? {
            val style = element.attributes[STYLE] ?: return null
            val pattern = STYLE_REGEX.toPattern()
            val matcher = pattern.matcher(style)
            while (matcher.find()) {
                val key = matcher.group(1) ?: continue
                if (key == name) {
                    return matcher.group(2) ?: continue
                }
            }
            return null
        }

        /**
         * Set the 'align' style attribute which sets the [AlignmentSpan] on text.
         *
         * @param align One of the following: [Align.CENTER], [Align.LEFT], or [Align.RIGHT].
         * @return This [Style] instance for chaining calls.
         */
        fun align(align: Align) = apply {
            this[ALIGN] = align.value
        }

        /**
         * Set the 'background-color' style attribute which sets the [BackgroundColorSpan] on text.
         *
         * @param color The color integer.
         * @return This [Style] instance for chaining calls.
         */
        fun background(color: Int) = apply {
            this[BACKGROUND_COLOR] = color.hex
        }

        /**
         * Set the 'color' style attribute which sets the [ForegroundColorSpan] on text.
         *
         * @param color The color integer.
         * @return This [Style] instance for chaining calls.
         */
        fun color(color: Int) = apply {
            this[COLOR] = color.hex
        }

        /**
         * Set the 'text-decoration' style attribute to 'line-through' which sets the
         * [StrikethroughSpan] on text.
         *
         * @return This [Style] instance for chaining calls.
         */
        fun strikethrough() = apply {
            this[TEXT_DECORATION] = LINE_THROUGH
        }

        /**
         * Set the 'text-align' style attribute which sets the [AlignmentSpan] on text.
         *
         * @param align One of the following: [Align.CENTER], [Align.LEFT], or [Align.RIGHT].
         * @return This [Style] instance for chaining calls.
         */
        fun textAlign(align: Align) = apply {
            this[TEXT_ALIGN] = align.value
        }

        override fun render(): String {
            return element.attributes[STYLE] ?: ""
        }

        override fun toString(): String = render()

        companion object {
            private const val STYLE = "style"
            private const val ALIGN = "align"
            private const val BACKGROUND_COLOR = "background-color"
            private const val COLOR = "color"
            private const val TEXT_DECORATION = "text-decoration"
            private const val TEXT_ALIGN = "text-align"
            private const val LINE_THROUGH = "line-through"

            //-------------------------------- CSS NAME -------------------------- VALUE ---
            private const val STYLE_REGEX = "(-?[_a-zA-Z]+[_a-zA-Z0-9-]*)\\s*:\\s*([^;]+);?"

            /** Convert an integer to a hex color code. */
            private val Int.hex: String get() = '#' + String.format("%06X", 0xFFFFFF and this)
        }
    }
}
