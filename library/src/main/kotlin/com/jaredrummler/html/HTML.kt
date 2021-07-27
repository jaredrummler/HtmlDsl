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
import android.widget.TextView

/**
 * This class is used to provide a DSL to build valid HTML for a [TextView].
 *
 * Example usage:
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
 *
 * @see html
 * @see android.text.Html
 */
class HTML internal constructor() : Node {

    private val elements = mutableListOf<HtmlElement>()

    private val renderer: HtmlRenderer<StringBuilder>
        get() = HtmlRenderer(this, StringBuilder(), false)

    override val children: List<HtmlElement> get() = elements.toList()

    override val parent: HtmlElement? = null

    override fun add(child: HtmlElement) = elements.add(child)

    override fun remove(child: HtmlElement) = elements.remove(child)

    /**
     * Render elements as valid HTML.
     *
     * @param prettyPrint True to indent and add new-lines to the produced HTML.
     */
    fun render(prettyPrint: Boolean = false): String {
        return renderer.setPrettyPrint(prettyPrint).render().toString().trimEnd()
    }

    override fun toString(): String = render(false)

    companion object {

        /**
         * Create a new instance of [HTML] to be rendered by [Html.fromHtml] and set on a [TextView]
         *
         * @param init Block of code to create child elements.
         * @return The instance of [HTML] with the applied [init] block.
         * @see html
         */
        fun create(init: HTML.() -> Unit) = HTML().apply(init)
    }
}
