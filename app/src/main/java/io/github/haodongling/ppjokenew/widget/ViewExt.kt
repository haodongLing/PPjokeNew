package io.github.haodongling.ppjokenew.widget

import android.view.View
import io.github.haodongling.ppjokenew.R
import android.util.AttributeSet

/**
 * Author: tangyuan
 * Time : 2021/11/24
 * Description:
 */
class ViewExt {
    val RADIUS_ALL = 0
    val RADIUS_LEFT = 1
    val RADIUS_TOP = 2
    val RADIUS_RIGHT = 3
    val RADIUS_BOTTOM = 4

    fun View.setOutline(attributes: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {
        val array =
            context.obtainStyledAttributes(attributes, R.styleable.viewOutLineStrategy, defStyleAttr, defStyleRes)
        var radius = array.getDimensionPixelOffset(R.styleable.viewOutLineStrategy_clip_radius, 0)
        val hideSide: Int = array.getInt(R.styleable.viewOutLineStrategy_clip_side, 0);
        array.recycle()
        setOutlineInternal(radius, hideSide)
    }

    fun View.setOutlineInternal(radius: Int, radiusSide: Int) {

    }

}