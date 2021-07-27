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

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.Html
import android.text.method.LinkMovementMethod
import android.util.TypedValue
import android.widget.FrameLayout.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.view.setPadding
import com.blacksquircle.ui.editorkit.theme.EditorTheme
import com.blacksquircle.ui.editorkit.widget.TextProcessor
import com.blacksquircle.ui.language.base.Language
import com.blacksquircle.ui.language.html.HtmlLanguage
import com.blacksquircle.ui.language.kotlin.KotlinLanguage
import kotlin.math.roundToInt

class HtmlDemoView(context: Context?) : ScrollView(context) {

    private val container = LinearLayout(context).apply {
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        orientation = LinearLayout.VERTICAL
    }

    init {
        layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        addView(container)
    }

    fun init(preview: HtmlPreview) = apply {
        container.addView(labelView(context.getString(R.string.title_dsl)))
        container.addView(syntaxHighlightView(KotlinLanguage(), preview.kotlin))
        container.addView(labelView(context.getString(R.string.title_html)))
        container.addView(syntaxHighlightView(HtmlLanguage(), preview.html.render(true)))
        container.addView(labelView(context.getString(R.string.title_result)))
        container.addView(TextView(context).apply {
            text = HtmlCompat.fromHtml(
                preview.html.render(), HtmlCompat.FROM_HTML_MODE_LEGACY,
                HttpImageGetter(this), null
            )
            movementMethod = LinkMovementMethod.getInstance()
            setPadding(16.dp, 16.dp, 16.dp, 16.dp)
            setBackgroundColor(Color.WHITE)
        })
    }

    private fun syntaxHighlightView(language: Language, content: String) =
        TextProcessor(context).apply {
            this.language = language
            this.colorScheme = EditorTheme.MONOKAI
            this.editorConfig.highlightCurrentLine = false
            this.isEnabled = false
            this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
            this.setTextContent(content)
        }

    private fun labelView(label: String) =
        TextView(context).apply {
            typeface = Typeface.DEFAULT_BOLD
            text = label
            setPadding(4.dp)
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        }

    private val Int.dp: Int get() = (this * context.resources.displayMetrics.density).roundToInt()
}