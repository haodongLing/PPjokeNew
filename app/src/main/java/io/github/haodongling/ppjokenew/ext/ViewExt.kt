package io.github.haodongling.ppjokenew.ext

import android.annotation.TargetApi
import android.graphics.Outline
import android.view.View
import io.github.haodongling.ppjokenew.R
import android.util.AttributeSet
import android.view.ViewOutlineProvider
import io.github.haodongling.ppjokenew.ext.ViewExt.Companion.RADIUS_ALL
import io.github.haodongling.ppjokenew.ext.ViewExt.Companion.RADIUS_BOTTOM
import io.github.haodongling.ppjokenew.ext.ViewExt.Companion.RADIUS_LEFT
import io.github.haodongling.ppjokenew.ext.ViewExt.Companion.RADIUS_RIGHT
import io.github.haodongling.ppjokenew.ext.ViewExt.Companion.RADIUS_TOP

/**
 * Author: tangyuan
 * Time : 2021/11/24
 * Description:
 */
class ViewExt {
    companion object {
        const val RADIUS_ALL = 0
        const val RADIUS_LEFT = 1
        const val RADIUS_TOP = 2
        const val RADIUS_RIGHT = 3
        const val RADIUS_BOTTOM = 4

    }


}

fun View.setOutline(attributes: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {
    val array = context.obtainStyledAttributes(attributes, R.styleable.viewOutLineStrategy, defStyleAttr, defStyleRes)
    var radius = array.getDimensionPixelOffset(R.styleable.viewOutLineStrategy_clip_radius, 0)
    val hideSide: Int = array.getInt(R.styleable.viewOutLineStrategy_clip_side, 0);
    array.recycle()
    setOutlineInternal(radius, hideSide)
}

fun View.setOutlineInternal(radius: Int, radiusSide: Int) {
    outlineProvider = object : ViewOutlineProvider() {
        @TargetApi(21)
        override fun getOutline(view: View, outline: Outline) {
            val w = view.width
            val h = view.height
            if (w == 0 || h == 0) {
                return
            }
            if (radiusSide != RADIUS_ALL) {
                var left = 0
                var top = 0
                var right = w
                var bottom = h
                if (radiusSide == RADIUS_LEFT) {
                    right += radius
                } else if (radiusSide == RADIUS_TOP) {
                    bottom += radius
                } else if (radiusSide == RADIUS_RIGHT) {
                    left -= radius
                } else if (radiusSide == RADIUS_BOTTOM) {
                    top -= radius
                }
                outline.setRoundRect(left, top, right, bottom, radius.toFloat())
                return
            }
            val top = 0
            val left = 0
            if (radius <= 0) {
                outline.setRect(left, top, w, h)
            } else {
                outline.setRoundRect(left, top, w, h, radius.toFloat())
            }
        }
    }
    clipToOutline = radius > 0
    invalidate()
}