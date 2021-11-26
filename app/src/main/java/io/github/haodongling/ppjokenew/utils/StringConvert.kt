package io.github.haodongling.ppjokenew.utils

import android.graphics.Color
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan
import android.graphics.Typeface

object StringConvert {
    @JvmStatic
    fun convertFeedUgc(count: Int): String {
        return if (count < 10000) {
            this.toString()
        } else "${count / 10000}万"
    }
    @JvmStatic
    fun convertTagFeedList(num: Int): String {
        return if (num < 10000) {
            "${num}人观看"
        } else {
            "${num / 1000}万人观看"
        }
    }
    @JvmStatic
    fun convertSpannable(count: Int, intro: String): CharSequence {
        val countStr = count.toString()
        val ss = SpannableString(countStr + intro)
        ss.setSpan(ForegroundColorSpan(Color.BLACK), 0, countStr.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ss.setSpan(AbsoluteSizeSpan(16, true), 0, countStr.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ss.setSpan(StyleSpan(Typeface.BOLD), 0, countStr.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return ss
    }
}