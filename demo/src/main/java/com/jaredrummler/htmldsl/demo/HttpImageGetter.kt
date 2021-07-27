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

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.text.Html
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import java.lang.ref.WeakReference

class HttpImageGetter(view: TextView) : Html.ImageGetter {

    private val reference = WeakReference(view)
    private var density = DEFAULT_DENSITY
    private var matchParentWidth = false

    fun config(
        densityAware: Boolean = false,
        matchParentWidth: Boolean = false
    ): HttpImageGetter = apply {
        this.matchParentWidth = matchParentWidth
        this.density = when (densityAware) {
            true -> reference.require().resources.displayMetrics.density
            else -> DEFAULT_DENSITY
        }
    }

    override fun getDrawable(source: String): Drawable {
        return BitmapDrawablePlaceholder().also { target ->
            val view = reference.require()
            view.post {
                Glide.with(view.context)
                    .asBitmap()
                    .load(source)
                    .into(target)
            }
        }
    }

    private inner class BitmapDrawablePlaceholder(
        resources: Resources = reference.require().resources
    ) : BitmapDrawable(
        resources, Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
    ), Target<Bitmap> {

        private var drawable: Drawable? = null
            set(value) {
                field = value
                if (value != null) {
                    setBounds(value)
                    reference.get()?.let { view ->
                        view.text = view.text
                    }
                }
            }

        private fun setBounds(drawable: Drawable) {
            val view = reference.get() ?: return
            val drawableWidth = (drawable.intrinsicWidth * density).toInt()
            val drawableHeight = (drawable.intrinsicHeight * density).toInt()
            val maxWidth = view.measuredWidth
            if (drawableWidth > maxWidth || matchParentWidth) {
                val calculatedHeight = maxWidth * drawableHeight / drawableWidth
                drawable.setBounds(0, 0, maxWidth, calculatedHeight)
                setBounds(0, 0, maxWidth, calculatedHeight)
            } else {
                drawable.setBounds(0, 0, drawableWidth, drawableHeight)
                setBounds(0, 0, drawableWidth, drawableHeight)
            }
        }

        override fun draw(canvas: Canvas) {
            drawable?.draw(canvas)
        }

        override fun onLoadStarted(placeholder: Drawable?) {
            drawable = placeholder
        }

        override fun onLoadFailed(errorDrawable: Drawable?) {
            drawable = errorDrawable
        }

        override fun onLoadCleared(placeholder: Drawable?) {
            drawable = placeholder
        }

        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
            drawable = BitmapDrawable(reference.require().resources, resource)
        }

        override fun getSize(cb: SizeReadyCallback) {
            cb.onSizeReady(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
        }

        override fun onStart() {
            // no-op
        }

        override fun onStop() {
            // no-op
        }

        override fun onDestroy() {
            // no-op
        }

        override fun removeCallback(cb: SizeReadyCallback) {
            // no-op
        }

        override fun setRequest(request: Request?) {
            // no-op
        }

        override fun getRequest(): Request? {
            return null
        }
    }

    companion object {
        private const val DEFAULT_DENSITY = 1.00f
        private inline fun <reified T : Any> WeakReference<T>.require(): T = requireNotNull(get())
    }
}