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

class HTMLTest {

    @Test
    fun `should create html with single element`() {
        val expectedHtml = "<a href=\"https://google.com\">Google</a>"
        val html = HTML.create {
            a {
                href = "https://google.com"
                text = "Google"
            }
        }
        Assert.assertTrue(html.children.size == 1)
        Assert.assertTrue(html.children[0] is A)
        Assert.assertTrue(html.children[0].parent == null)
        Assert.assertEquals(expectedHtml, html.render())
    }

    @Test
    fun `should pretty print html`() {
        val html = html {
            h3("Android Versions")
            br()
            p {
                strong("The version history of the Android mobile operating system:")
            }
            ul {
                li("Cupcake")
                li("Donut")
                li("Eclair")
                li("Froyo")
                li("Gingerbread")
                li("Honeycomb")
                li("Ice Cream Sandwich")
                li("Jelly Bean")
                li("KitKat")
                li("Lollipop")
                li("Marshmallow")
                li("Nougat")
                li("Oreo")
                li("Pie")
                li("Android 10")
                li("Android 11")
                li("Android 12")
            }
            div(Attribute.Align.RIGHT) {
                p {
                    +"lorem ipsum dolor sit amet..."
                }
            }
        }

        val expected = """
            <h3>Android Versions</h3>
            <br>
            <p><strong>The version history of the Android mobile operating system:</strong></p>
            <ul>
              <li>Cupcake</li>
              <li>Donut</li>
              <li>Eclair</li>
              <li>Froyo</li>
              <li>Gingerbread</li>
              <li>Honeycomb</li>
              <li>Ice Cream Sandwich</li>
              <li>Jelly Bean</li>
              <li>KitKat</li>
              <li>Lollipop</li>
              <li>Marshmallow</li>
              <li>Nougat</li>
              <li>Oreo</li>
              <li>Pie</li>
              <li>Android 10</li>
              <li>Android 11</li>
              <li>Android 12</li>
            </ul>
            <div align="right">
              <p>lorem ipsum dolor sit amet...</p>
            </div>
        """.trimIndent()

        val source = html.render(true)
        Assert.assertEquals(expected, source)
    }

    @Test
    fun `should escape text when rendering`() {
        val html = html {
            h1("Heading")
            div {
                p {
                    +"I am so fucking ${Sup("high")} right now"
                }
            }
        }

        val expected = """
            <h1>Heading</h1>
            <div>
              <p>I am so fucking &lt;sup&gt;high&lt;/sup&gt; right now</p>
            </div>
        """.trimIndent()

        Assert.assertEquals(expected, html.render(true))
    }

    @Test
    fun `should not escape text in unsafe block`() {
        val html = html {
            h1("Heading")
            div {
                p {
                    unsafe {
                        +"I am so fucking ${Sup("high")} right now"
                    }
                }
            }
        }

        val expected = """
            <h1>Heading</h1>
            <div>
              <p>I am so fucking <sup>high</sup> right now</p>
            </div>
        """.trimIndent()

        Assert.assertEquals(expected, html.render(true))
    }
}
