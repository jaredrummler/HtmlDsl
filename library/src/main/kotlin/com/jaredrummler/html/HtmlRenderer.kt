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

import java.io.Writer

/**
 * Creates valid HTML from [HTML] or [elements][HtmlElement] using a [Writer] or [Appendable].
 *
 * @param elements The elements to render into the HTML source.
 * @param writer The destination to write to (e.g. [StringBuilder]).
 * @param pretty True to pretty print the HTML (indent, new lines)
 * @param Out [Writer] or [Appendable], such as [StringBuilder].
 */
class HtmlRenderer<out Out : Appendable>(
    private val elements: List<HtmlElement>,
    private val writer: Out,
    private var pretty: Boolean
) : Renderer<Out> {

    private var ln = true
    private var level = 0

    constructor(
        html: HTML, writer: Out, pretty: Boolean
    ) : this(html.children, writer, pretty)

    constructor(
        element: HtmlElement, writer: Out, pretty: Boolean
    ) : this(listOf(element), writer, pretty)

    /**
     * Set whether or not to format the HTML when rendering.
     *
     * @param enable True to pretty print the HTML
     * @return This instance for chaining of method calls.
     */
    fun setPrettyPrint(enable: Boolean) = apply {
        pretty = enable
    }

    override fun render(): Out {
        elements.forEach(::render)
        return writer
    }

    private fun render(element: HtmlElement) {
        renderStart(element)
        renderContent(element)
        renderEnd(element)
    }

    private fun renderStart(element: HtmlElement) {
        if (pretty && !element.inlineTag) {
            indent()
        }
        level++
        writer.append('<')
        writer.append(element.tagName)
        if (element.attributes.size > 0) {
            writer.append(' ')
            writer.append(element.attributes.render())
        }
        writer.append('>')
    }

    private fun renderContent(element: HtmlElement) {
        if (element is TextElement) {
            when (val text = element.text) {
                is HtmlElement -> render(text)
                is CharSequence -> {
                    if (element.unsafe) {
                        writer.append(text)
                    } else {
                        writer.escapeAppend(text)
                    }
                }
                else -> writer.append(text.toString())
            }
            ln = false
        }
        for (child in element.children) {
            render(child)
        }
    }

    private fun renderEnd(element: HtmlElement) {
        level--
        if (ln) {
            indent()
        }
        if (!element.emptyTag) {
            writer.append("</")
            writer.append(element.tagName)
            writer.append(">")
        }
        if (!element.inlineTag) {
            writer.maybeAppendNewLine()
        }
    }

    private fun indent() {
        if (pretty) {
            if (!ln) {
                writer.append("\n")
            }
            var remaining = level
            while (remaining >= 4) {
                writer.append("        ")
                remaining -= 4
            }
            while (remaining >= 2) {
                writer.append("    ")
                remaining -= 2
            }
            if (remaining > 0) {
                writer.append("  ")
            }
            ln = false
        }
    }

    private fun Out.maybeAppendNewLine() {
        if (pretty && !ln) {
            append('\n')
            ln = true
        }
    }

    companion object {

        private val escapeMap by lazy {
            mapOf(
                '<' to "&lt;", '>' to "&gt;", '&' to "&amp;", '\"' to "&quot;"
            ).let { mappings ->
                val size = mappings.keys.map { ch -> ch.code }.maxOf { it } + 1
                Array(size) { mappings[it.toChar()] }
            }
        }

        private fun Appendable.escapeAppend(s: CharSequence) {
            var lastIndex = 0
            val mappings = escapeMap
            val size = mappings.size

            for (idx in s.indices) {
                val ch = s[idx].code
                if (ch < 0 || ch >= size) continue
                val escape = mappings[ch]
                if (escape != null) {
                    append(s.substring(lastIndex, idx))
                    append(escape)
                    lastIndex = idx + 1
                }
            }

            if (lastIndex < s.length) {
                append(s.substring(lastIndex, s.length))
            }
        }

        private val Tag.inlineTag: Boolean get() = inlineTags.contains(tagName)

        private val Tag.emptyTag: Boolean get() = emptyTags.contains(tagName)

        private val inlineTags = arrayOf(
            "a", "abbr", "acronym", "b", "bdo", "big", "br", "button", "cite", "code", "dfn",
            "em", "i", "img", "input", "kbd", "label", "map", "object", "output", "q", "samp",
            "script", "select", "small", "span", "strong", "sub", "sup", "textarea", "time",
            "tt", "var"
        )

        private val emptyTags = arrayOf(
            "area", "base", "br", "col", "embed", "hr", "img", "input", "link", "meta", "param",
            "source", "track", "wbr"
        )
    }
}