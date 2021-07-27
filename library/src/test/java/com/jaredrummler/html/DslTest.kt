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

class DslTest {

    @Test
    fun `should render a HTML element`() {
        val expected = """<a href="https://google.com">Google</a>"""
        val rendered = html {
            a {
                href = "https://google.com"
                text = "Google"
            }
        }.render()
        Assert.assertEquals(expected, rendered)
    }

    @Test
    fun `should render b HTML element`() {
        val expected = """<b>This text is bold</b>"""
        val rendered = html {
            b {
                text = "This text is bold"
            }
        }.render()
        Assert.assertEquals(expected, rendered)
    }


    @Test
    fun `should render big HTML element`() {
        val expected = """<big>That's what she said</big>"""
        val rendered = html {
            big {
                text = "That's what she said"
            }
        }.render()
        Assert.assertEquals(expected, rendered)
    }

    @Test
    fun `should render blockquote HTML element`() {
        val expected = """<blockquote>Your wife is in me DMs</blockquote>""" // Conor McGregor
        val rendered = html {
            blockquote {
                text = "Your wife is in me DMs"
            }
        }.render()
        Assert.assertEquals(expected, rendered)
    }

    @Test
    fun `should render br HTML element`() {
        val expected = """<br>"""
        val rendered = html {
            br()
        }
            .render()
        Assert.assertEquals(expected, rendered)
    }

    @Test
    fun `should render cite HTML element`() {
        val expected = """<cite>Italic text using HTML cite tag</cite>"""
        val rendered = html {
            cite {
                text = "Italic text using HTML cite tag"
            }
        }.render()
        Assert.assertEquals(expected, rendered)
    }


    @Test
    fun `should render del HTML element`() {
        val expected = """<del><p>This paragraph has been deleted.</p></del>"""
        val rendered = html {
            del {
                p {
                    text = "This paragraph has been deleted."
                }
            }
        }.render()
        Assert.assertEquals(expected, rendered)
    }

    @Test
    fun `should render dfn HTML element`() {
        val expected = """<dfn>Italic text using HTML dfn tag</dfn>"""
        val rendered = html {
            dfn {
                text = "Italic text using HTML dfn tag"
            }
        }.render()
        Assert.assertEquals(expected, rendered)
    }

    @Test
    fun `should render div HTML element`() {
        val expected = """<div><p>This paragraph is inside a div.</p></div>"""
        val rendered = html {
            div {
                p {
                    text = "This paragraph is inside a div."
                }
            }
        }.render()
        Assert.assertEquals(expected, rendered)
    }

    @Test
    fun `should render em HTML element`() {
        val expected = """<em>Italic text using HTML em tag</em>"""
        val rendered = html {
            em {
                text = "Italic text using HTML em tag"
            }
        }.render()
        Assert.assertEquals(expected, rendered)
    }


    @Test
    fun `should render font HTML element`() {
        val expected =
            """<font face="sans-serif-condensed-light" color="red">Text with a custom font</font>"""
        val rendered = html {
            font {
                face = "sans-serif-condensed-light" // RobotoCondensed-Light.ttf
                text = "Text with a custom font"
                color = "red"
            }
        }.render()
        Assert.assertEquals(expected, rendered)
    }

    @Test
    fun `should render i HTML element`() {
        val expected = """<i>Italic text using HTML i tag</i>"""
        val rendered = html {
            i {
                text = "Italic text using HTML i tag"
            }
        }.render()
        Assert.assertEquals(expected, rendered)
    }


    @Test
    fun `should render img HTML element`() {
        val expected = """<img src="image.png">"""
        val rendered = html {
            img {
                src = "image.png"
            }
        }.render()
        Assert.assertEquals(expected, rendered)
    }

    @Test
    fun `should render h1 HTML element`() {
        val expected = """<h1>Heading 1</h1>"""
        val rendered = html {
            h1 {
                text = "Heading 1"
            }
        }.render()
        Assert.assertEquals(expected, rendered)
    }

    @Test
    fun `should render h2 HTML element`() {
        val expected = """<h2>Heading 2</h2>"""
        val rendered = html {
            h2 {
                text = "Heading 2"
            }
        }.render()
        Assert.assertEquals(expected, rendered)
    }

    @Test
    fun `should render h3 HTML element`() {
        val expected = """<h3>Heading 3</h3>"""
        val rendered = html {
            h3 {
                text = "Heading 3"
            }
        }.render()
        Assert.assertEquals(expected, rendered)
    }

    @Test
    fun `should render h4 HTML element`() {
        val expected = """<h4>Heading 4</h4>"""
        val rendered = html {
            h4 {
                text = "Heading 4"
            }
        }.render()
        Assert.assertEquals(expected, rendered)
    }

    @Test
    fun `should render h5 HTML element`() {
        val expected = """<h5>Heading 5</h5>"""
        val rendered = html {
            h5 {
                text = "Heading 5"
            }
        }.render()
        Assert.assertEquals(expected, rendered)
    }

    @Test
    fun `should render h6 HTML element`() {
        val expected = """<h6>Heading 6</h6>"""
        val rendered = html {
            h6 {
                text = "Heading 6"
            }
        }.render()
        Assert.assertEquals(expected, rendered)
    }

    @Test
    fun `should render ul HTML element`() {
        val expected = """<ul><li>List item text with a bullet</li></ul>"""
        val rendered = html {
            ul {
                li {
                    text = "List item text with a bullet"
                }
            }
        }.render()
        Assert.assertEquals(expected, rendered)
    }

    @Test
    fun `should render small HTML element`() {
        val expected = """<small>This text is scaled down by 0.8</small>"""
        val rendered = html {
            small {
                text = "This text is scaled down by 0.8"
            }
        }.render()
        Assert.assertEquals(expected, rendered)
    }


    @Test
    fun `should render strong HTML element`() {
        val expected = """<strong>This text is bold</strong>"""
        val rendered = html {
            strong {
                text = "This text is bold"
            }
        }.render()
        Assert.assertEquals(expected, rendered)
    }


    @Test
    fun `should render sub HTML element`() {
        val expected = """<sub>This text is lower</sub>"""
        val rendered = html {
            sub {
                text = "This text is lower"
            }
        }.render()
        Assert.assertEquals(expected, rendered)
    }

    @Test
    fun `should render sup HTML element`() {
        val expected = """<sup>This text is higher</sup>"""
        val rendered = html {
            sup {
                text = "This text is higher"
            }
        }.render()
        Assert.assertEquals(expected, rendered)
    }

    @Test
    fun `should render tt HTML element`() {
        val expected = """<tt>Text in monospace typeface</tt>"""
        val rendered = html {
            tt {
                text = "Text in monospace typeface"
            }
        }.render()
        Assert.assertEquals(expected, rendered)
    }

    @Test
    fun `should render u HTML element`() {
        val expected = """<u>This text has an underline.</u>"""
        val rendered = html {
            u {
                text = "This text has an underline."
            }
        }.render()
        Assert.assertEquals(expected, rendered)
    }
}