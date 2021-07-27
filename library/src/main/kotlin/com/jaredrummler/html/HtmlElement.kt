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
 * The base class for an HTML element.
 *
 * Valid tags supported by [android.text.Html] include:
 *
 * [A], [Target], [B], [Big], [BlockQuote], [BR], [Cite], [Del], [Dfn], [Div], [EM], [Font],
 * [H1], [H2], [H3], [H4], [H5], [H6], [I], [Img], [LI], [P], [S], [Small], [Span], [Strike],
 * [Strong], [Sub], [Sup], [TT], [U], [UL]
 *
 * @param tagName The tag name for the element.
 */
@HtmlTagMarker
abstract class HtmlElement(override val tagName: String) : Tag, Node {

    /**
     * A collection of attributes of this element.
     */
    val attributes = Attributes()

    /**
     * Used to add, remove, or modify the 'style' attribute.
     */
    val style by lazy { Attribute.Style(this) }

    override val children: List<HtmlElement> get() = _children.toList()

    override var parent: HtmlElement? = null

    override fun add(child: HtmlElement) = _children.add(child)

    override fun remove(child: HtmlElement): Boolean = _children.remove(child)

    override fun toString(): String = html()

    /**
     * Create/Append a 'style' attribute which is added to the [attributes] when rendering the HTML.
     * This will append " [key]=[value];" to the 'style' attribute.
     *
     * @param key The CSS attribute name.
     * @param value The CSS attribute value.
     */
    fun style(key: String, value: String) = style.set(key, value)

    /**
     * Adds an [attribute][Attribute] to the element's [attributes].
     *
     * @param attribute The attribute to add.
     * @return `true` if the element has been added, `false` if the element is already contained.
     */
    fun attr(attribute: Attribute) = attributes.add(attribute)

    /**
     * Creates an Attribute from the pair and adds it to the element's [attributes].
     *
     * @param attr The Pair, using [Pair.first] as the attribute key and [Pair.second] as the value.
     * @return `true` if the element has been added, `false` if the element is already contained.
     */
    fun attr(attr: Pair<String, String?>) = attr(attr.first, attr.second)

    /**
     * Creates an Attribute from the key/value and adds it to the element's [attributes].
     *
     * @param key The CSS attribute name.
     * @param value The Enum containing the CSS attribute value.
     * @return `true` if the element has been added, `false` if the element is already contained.
     */
    fun attr(key: String, value: Attribute.Value?) = attr(key, value?.value)

    /**
     * Adds an [attribute][Attribute] to the element's [attributes] or removes the attribute
     * (it the key exists) if the [value] is null.
     *
     * @param key The CSS attribute name.
     * @param value The CSS attribute value or `null` to remove the attribute by its key.
     * @return `true` if the element has been added, `false` if the element is already contained.
     */
    fun attr(key: String, value: String?) = when (value) {
        null -> attributes.remove(key)
        else -> attr(Attribute(key, value))
    }

    /**
     * Renders this [HtmlElement] as valid HTML.
     */
    fun html(): String = renderer.render().toString()

    /**
     * The HTML string.
     */
    open val outerHTML: String get() = html()

    /**
     * The HTML string containing this elements [children].
     */
    open val innerHTML: String
        get() = HtmlRenderer(children, StringBuilder(), false).render().toString()

    private val renderer: HtmlRenderer<StringBuilder>
        get() = HtmlRenderer(this, StringBuilder(), false)

    private val _children = mutableListOf<HtmlElement>()
}