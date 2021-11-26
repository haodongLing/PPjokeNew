package io.github.haodongling.ppjokenew.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import io.github.haodongling.ppjokenew.ext.setOutline

/**
 * Author: tangyuan
 * Time : 2021/11/25
 * Description:
 */
class CornerFrameLayout : FrameLayout {
    constructor(context: Context) : super(context, null)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr, 0)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context, attrs, defStyleAttr, defStyleRes
    ) {
        setOutline(attrs, defStyleAttr, defStyleRes = defStyleRes)
    }


}