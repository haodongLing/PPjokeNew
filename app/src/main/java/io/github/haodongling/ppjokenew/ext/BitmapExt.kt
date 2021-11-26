package io.github.haodongling.ppjokenew.ext

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.*

/**
 * Author: tangyuan
 * Time : 2021/11/25
 * Description:
 */

fun Bitmap.doBlur(radius: Int, canReuseInBitmap: Boolean, context: Context): Bitmap? {
    var bitmap: Bitmap

    bitmap = if (canReuseInBitmap) {
        this
    } else {
        this.copy(this.config, true)
    }

    if (bitmap.config == Bitmap.Config.RGB_565) {
        // RenderScript hates RGB_565 so we convert it to ARGB_8888
        // (see http://stackoverflow.com/questions/21563299/
        // defect-of-image-with-scriptintrinsicblur-from-support-library)
        bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
    }
    //Let's create an empty bitmap with the same size of the bitmap we want to blur
    //Let's create an empty bitmap with the same size of the bitmap we want to blur
    val outBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)

    try {
        val rs = RenderScript.create(context)
        val input = Allocation.createFromBitmap(
            rs, bitmap, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT
        )
        val output = Allocation.createTyped(rs, input.type)
        val script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        script.setRadius(radius.toFloat())
        script.setInput(input)
        script.forEach(output)
        output.copyTo(outBitmap)
        bitmap.recycle()
        script.destroy()
        return outBitmap
    } catch (e: RSRuntimeException) {
    }

    return null
}
