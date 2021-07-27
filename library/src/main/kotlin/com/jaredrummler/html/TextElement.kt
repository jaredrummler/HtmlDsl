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

/**
 * Use HTML text content elements to organize blocks or sections of content.
 *
 * @param tagName The name of the HTML element.
 */
abstract class TextElement(override val tagName: String) : HtmlElement(tagName) {

    /**
     * The content placed between the opening and closing tag name. This could be a String,
     * another [HtmlElement], or a number.
     */
    var text: HtmlText = ""
        set(value) {
            unsafe = false
            field = value
            when (value) {
                is HtmlElement -> {
                    remove(value)
                    value.parent = this
                }
            }
        }

    /**
     * Set to true to skip escaping HTML [text] when rendering.
     *
     * @see HtmlRenderer
     */
    var unsafe: Boolean = false
        get() {
            var parent: HtmlElement? = parent
            while (parent != null) {
                if (parent is TextElement) {
                    if (parent.unsafe) return true
                }
                parent = parent.parent
            }
            return field
        }

    /**
     * Construct a new instance of a [TextElement] with the [text] inlined.
     *
     * @param name The name of the HTML element.
     * @param text The content of the HTML element.
     */
    constructor(name: String, text: HtmlText) : this(name) {
        this.text = text
    }

    /**
     * Set the [text] to be rendered without escaping.
     *
     * @param text The content to set on this element.
     * @see unsafe
     */
    fun unsafe(text: HtmlText) = unsafe {
        this.text = text
    }

    /**
     * Set the [text] to be rendered without escaping.
     *
     * @param block Code to set the content of this element.
     */
    fun unsafe(block: TextElement.() -> Unit) = try {
        block()
    } finally {
        unsafe = true
    }

    /** Sets the [text]. */
    operator fun HtmlText.unaryPlus() {
        this@TextElement.text = this
    }

    override val innerHTML: String
        get() {
            val elements = mutableListOf<HtmlElement>().apply {
                (text as? HtmlElement)?.let { e -> add(e) }
                addAll(children)
            }.toList()
            val renderer = HtmlRenderer(elements, StringBuilder(), false)
            return renderer.render().toString()
        }
}
