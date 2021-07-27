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

import org.junit.Assert
import org.junit.Test

class AttributesTest {

    @Test
    fun `should add attribute to list`() {
        val attributes = Attributes()
        val attribute = Attribute("background-color", "gold")
        attributes["background-color"] = "gold"
        Assert.assertTrue(attributes.contains(attribute))
        attributes["href"] = "https://google.com"
        Assert.assertTrue(attributes["href"] == "https://google.com")
    }

    @Test
    fun `should render attributes`() {
        val attributes = Attributes()
        attributes["href"] = "https://google.com"
        attributes["target"] = "_blank"
        attributes["background-color"] = "gold"
        val expected = "href=\"https://google.com\" target=\"_blank\" background-color=\"gold\""
        Assert.assertEquals(expected, attributes.render())
    }

    @Test
    fun `should remove attribute by name`() {
        val attributes = Attributes()
        attributes["href"] = "https://google.com"
        attributes.remove("href")
        Assert.assertTrue(attributes.isEmpty())
    }
}