package io.github.haodongling.ppjokenew.exoplayer

import android.view.ViewGroup

/**
 * Author: tangyuan
 * Time : 2021/11/26
 * Description:
 */
interface IPlayTarget {
    fun getOwner():ViewGroup

    fun onActive()

    fun inActive()

    fun isPlaying()
}