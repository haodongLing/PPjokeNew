package io.github.haodongling.ppjokenew.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import io.github.haodongling.lib.common.util.PixUtils
import io.github.haodongling.ppjokenew.ext.setOutline

/**
 * Author: tangyuan
 * Time : 2021/11/24
 * Description:
 */
class PPImageView : AppCompatImageView {
    companion object {
        @BindingAdapter(value = ["image_url", "isCircle", "radius"], requireAll = false)
        @JvmStatic
        fun setImageUrl(view: PPImageView, imageUrl: String, isCircle: Boolean, radius: Int = 0) {
            val builder: RequestBuilder<Drawable> = Glide.with(view).load(imageUrl)
            if (isCircle) {
                builder.transform(CircleCrop())
            } else if (radius > 0) {
                builder.transform(RoundedCorners(PixUtils.dp2px(radius)))
            }
            val layoutParams: ViewGroup.LayoutParams = view.layoutParams
            if (layoutParams.width > 0 && layoutParams.height > 0) {
                builder.override(layoutParams.width, layoutParams.height)
            }
            builder.into(view)
        }

        fun getBlurImageUrl(view: ImageView, blurRrl: String, radius: Int) {

        }

    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attr: AttributeSet) : super(context, attr)
    constructor(context: Context, attr: AttributeSet, defStyle: Int) : super(context, attr, defStyle) {
        setOutline(attr, defStyle, 0)
    }

    fun bindData(widthPx: Int, heightPx: Int, marginLeft: Int, imageUrl: String) {
        bindData(widthPx, heightPx, marginLeft, PixUtils.getScreenWidth(), PixUtils.getScreenWidth(), imageUrl)
    }

    fun bindData(widthPx: Int, heightPx: Int, marginLeft: Int, maxWidth: Int, maxHeight: Int, imageUrl: String) {
        if (TextUtils.isEmpty(imageUrl)) {
            visibility = GONE
            return
        } else {
            visibility = VISIBLE
        }
        if (widthPx <= 0 || heightPx <= 0) {
            Glide.with(this).load(imageUrl).into(object : SimpleTarget<Drawable?>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable?>?) {
                    val height = resource.intrinsicHeight
                    val width = resource.intrinsicWidth
                    setSize(width, height, marginLeft, maxWidth, maxHeight)
                    setImageDrawable(resource)
                }
            })
            return
        }
        setSize(widthPx, heightPx, marginLeft, maxWidth, maxHeight)
        setImageUrl(this, imageUrl, false)
    }

    private fun setSize(width: Int, height: Int, marginLeft: Int, maxWidth: Int, maxHeight: Int) {
        val finalWidth: Int
        val finalHeight: Int
        if (width > height) {
            finalWidth = maxWidth
            finalHeight = (height / (width * 1.0f / finalWidth)).toInt()
        } else {
            finalHeight = maxHeight
            finalWidth = (width / (height * 1.0f / finalHeight)).toInt()
        }
        val params = layoutParams
        params.width = finalWidth
        params.height = finalHeight
        if (params is FrameLayout.LayoutParams) {
            params.leftMargin = if (height > width) PixUtils.dp2px(marginLeft) else 0
        } else if (params is LinearLayout.LayoutParams) {
            params.leftMargin = if (height > width) PixUtils.dp2px(marginLeft) else 0
        }
        layoutParams = params
    }
}
