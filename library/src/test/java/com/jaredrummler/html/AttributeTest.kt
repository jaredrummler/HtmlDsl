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

import android.graphics.Color
import org.junit.Assert
import org.junit.Test

class AttributeTest {

    @Test
    fun `should create HTML attribute`() {
        val attr = Attribute("href", "https://google.com")
        Assert.assertEquals("href", attr.key)
        Assert.assertEquals("https://google.com", attr.value)
        Assert.assertEquals("href=\"https://google.com\"", attr.toString())
    }

    @Test
    fun `should create style attribute`() {
        val element = H1("header")
        val style = Attribute.Style(element)
        style.align(Attribute.Align.CENTER)
        Assert.assertEquals(style.render(), "align:center;")
    }

    @Test
    fun `should append style attribute`() {
        val style = Attribute.Style(H1())
        style.align(Attribute.Align.CENTER)
        style.background(Color.RED)
        Assert.assertEquals(style.render(), "align:center;background-color:#FF0000;")
    }
}