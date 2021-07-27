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

package com.jaredrummler.htmldsl.demo

import com.jaredrummler.html.*

enum class HtmlPreview(val kotlin: String, val html: HTML) {
    a("""
        font(color = "#1da1f2") {
            a(href = "https://twitter.com/jaredrummler", text = "Twitter")
        }.br()
        font(color = "#333") {
            a(href = "https://github.com/jaredrummler", text = "Github")
        }.br()
        font(color = "#ef8236") {
            a(href = "https://stackoverflow.com/users/1048340/jared-rummler") {
                +"StackOverflow"
            }
        }""".trimIndent(), html {
        font(color = "#1da1f2") {
            a(href = "https://twitter.com/jaredrummler", text = "Twitter")
        }.br()
        font(color = "#333") {
            a(href = "https://github.com/jaredrummler", text = "Github")
        }.br()
        font(color = "#ef8236") {
            a(href = "https://stackoverflow.com/users/1048340/jared-rummler") {
                +"StackOverflow"
            }
        }
    }),

    b("""
        b {
          +"This text is bold"
        }""".trimIndent(), html {
        b {
            +"This text is bold"
            unsafe("This text is <b>bold</b>")
        }
    }),

    big("""
        big {
            +"That's what she said"
        }""".trimIndent(), html {
        big {
            +"That's what she said"
        }
    }),

    blockquote("""
        blockquote {
            +"Your wife is in me DMs"
        }""".trimIndent(), html {
        blockquote {
            +"Your wife is in me DMs"
        }
    }),

    cite("""
        cite {
            +"Italic text using HTML cite tag"
        }""".trimIndent(), html {
        cite {
            +"Italic text using HTML cite tag"
        }
    }),

    del("""
        del {
            p {
                +"This paragraph has been deleted."
            }
        }""".trimIndent(), html {
        del {
            p {
                +"This paragraph has been deleted."
            }
        }
    }),

    dfn("""
        dfn {
            +"Italic text using HTML dfn tag"
        }""".trimIndent(), html {
        dfn {
            +"Italic text using HTML dfn tag"
        }
    }),

    div("""
        div {
            p {
                +"This paragraph is inside a div."
            }
        }""".trimIndent(), html {
        div {
            p {
                +"This paragraph is inside a div."
            }
        }
    }),

    em("""
        em {
            +"Italic text using HTML em tag"
        }""".trimIndent(), html {
        em {
            +"Italic text using HTML em tag"
        }
    }),

    font("""
        font {
            face = "sans-serif-condensed-light" // RobotoCondensed-Light.ttf
            text = "Text with a custom font"
            color = "red"
        }""".trimIndent(), html {
        font {
            face = "sans-serif-condensed-light" // RobotoCondensed-Light.ttf
            text = "Text with a custom font"
            color = "red"
        }
    }),

    i("""
        i {
            +"Italic text using HTML i tag"
        }""".trimIndent(), html {
        i {
            +"Italic text using HTML i tag"
        }
    }),

    img("""
        img {
            src = "https://services.google.com/fh/files/emails/image1_android_dev_newsletter.png"
        }""".trimIndent(), html {
        img {
            src = "https://services.google.com/fh/files/emails/image1_android_dev_newsletter.png"
        }
    }),

    h1("""
        h1 {
            +"Heading 1"
        }""".trimIndent(), html {
        h1 {
            +"Heading 1"
        }
    }),

    h2("""
        h2 {
            +"Heading 2"
        }""".trimIndent(), html {
        h2 {
            +"Heading 2"
        }
    }),

    h3("""
        h3 {
            +"Heading 3"
        }""".trimIndent(), html {
        h3 {
            +"Heading 3"
        }
    }),

    h4("""
        h4 {
            +"Heading 4"
        }""".trimIndent(), html {
        h4 {
            +"Heading 4"
        }
    }),

    h5("""
        h5 {
            +"Heading 5"
        }""".trimIndent(), html {
        h5 {
            +"Heading 5"
        }
    }),

    h6("""
        h6 {
            +"Heading 6"
        }""".trimIndent(), html {
        h6 {
            +"Heading 6"
        }
    }),

    ul("""
        h3("Primary Colors")
        ul {
            li {
                font(color = "#ff0000", text = "RED")
            }
            li {
                font(color = "#0000ff", text = "BLUE")
            }
            li {
                font(color = "#ffff00", text = "YELLOW")
            }
        }

        h3("Secondary Colors")
        ul {
            li {
                font(color = "#ff6600", text = "ORANGE")
            }
            li {
                font(color = "#00ff00", text = "GREEN")
            }
            li {
                font(color = "#6600FF", text = "PURPLE")
            }
        }""".trimIndent(), html {
        h3("Primary Colors")
        ul {
            li {
                font(color = "#ff0000", text = "RED")
            }
            li {
                font(color = "#0000ff", text = "BLUE")
            }
            li {
                font(color = "#ffff00", text = "YELLOW")
            }
        }

        h3("Secondary Colors")
        ul {
            li {
                font(color = "#ff6600", text = "ORANGE")
            }
            li {
                font(color = "#00ff00", text = "GREEN")
            }
            li {
                font(color = "#6600FF", text = "PURPLE")
            }
        }
    }),

    small("""
        small {
            +"This text is scaled down by 0.8"
        }""".trimIndent(), html {
        small {
            +"This text is scaled down by 0.8"
        }
    }),

    strong("""
        strong {
            +"This text is bold"
        }""".trimIndent(), html {
        strong {
            +"This text is bold"
        }
    }),

    sub("""
        sub {
            +"This text is lower"
        }""".trimIndent(), html {
        sub {
            +"This text is lower"
        }
    }),

    sup("""
        sup {
            +"This text is higher"
        }""".trimIndent(), html {
        sup {
            +"This text is higher"
        }
    }),

    tt("""
        tt {
            +"Text in monospace typeface"
        }""".trimIndent(), html {
        tt {
            +"Text in monospace typeface"
        }
    }),

    u("""
        u {
            +"This text has an underline."
        }""".trimIndent(), html {
        u {
            +"This text has an underline."
        }
    })
}
