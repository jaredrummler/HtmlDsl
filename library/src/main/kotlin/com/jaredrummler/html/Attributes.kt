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
 * The attributes of an Element.
 *
 * Attributes are treated as a set. There can be only one value associated with an
 * attribute key/name.
 *
 * Attribute name and value comparisons are generally <b>case sensitive</b>. By default for HTML,
 * attribute names are normalized to lower-case on parsing. That means you should use lower-case
 * strings when referring to attributes by name.
 *
 * @author Jared Rummler (jaredrummler@gmail.com)
 * @since July 6, 2021
 */
class Attributes : MutableCollection<Attribute>, Renderer<String> {

    private val attributes by lazy { mutableSetOf<Attribute>() }

    /**
     * Add an attribute to the collection.
     *
     * @param key The attribute name.
     * @param value The attribute value.
     * @return `true` if the element has been added, `false` if the attribute is already in the set.
     */
    operator fun set(key: String, value: String) = attributes.add(Attribute(key, value))

    /**
     * Get an attribute value from its name.
     *
     * @param key The attribute name.
     * @return The attribute value or null if no attribute with the provided name exists.
     */
    operator fun get(key: String): String? = attributes.firstOrNull { it.key == key }?.value

    /**
     * Add an attribute to the collection.
     *
     * @param attribute The attribute to add to the set.
     */
    operator fun plus(attribute: Attribute) = add(attribute)

    /**
     * Remove an attribute from the collection.
     *
     * @param key The attribute key.
     * @return True if collection contained an attribute with the provided key and removed it
     *         from the list.
     */
    fun remove(key: String): Boolean {
        val attr = attributes.firstOrNull { it.key == key } ?: return false
        return attributes.remove(attr)
    }

    override fun add(element: Attribute) = attributes.add(element)

    override fun contains(element: Attribute): Boolean = attributes.contains(element)

    override fun containsAll(elements: Collection<Attribute>) = attributes.containsAll(elements)

    override fun isEmpty(): Boolean = attributes.isEmpty()

    override fun addAll(elements: Collection<Attribute>): Boolean = attributes.addAll(elements)

    override fun clear() = attributes.clear()

    override fun remove(element: Attribute): Boolean = attributes.remove(element)

    override fun removeAll(elements: Collection<Attribute>) = attributes.removeAll(elements)

    override fun retainAll(elements: Collection<Attribute>) = attributes.retainAll(elements)

    override fun iterator(): MutableIterator<Attribute> = attributes.iterator()

    override fun render(): String = buildString {
        var separator = ""
        for ((attr, value) in attributes) {
            append(separator)
            append("$attr=\"$value\"")
            separator = " "
        }
    }

    override fun toString(): String = render()

    override val size get() = attributes.size
}
