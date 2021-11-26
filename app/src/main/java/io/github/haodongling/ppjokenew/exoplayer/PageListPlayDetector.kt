package io.github.haodongling.ppjokenew.exoplayer

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView

/**
 * Author: tangyuan
 * Time : 2021/11/26
 * Description:
 */
class PageListPlayDetector {
    private val mTargets = ArrayList<IPlayTarget>()
    private lateinit var mRecyclerView: RecyclerView
    private var playingTarget: IPlayTarget? = null
    fun addTarget(target: IPlayTarget) {
        mTargets.add(target)
    }

    fun removeTarget(target: IPlayTarget) {
        mTargets.remove(target)
    }

    constructor(owner: LifecycleOwner, recyclerView: RecyclerView) {
        mRecyclerView = recyclerView;
        owner.lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                when (event) {
                    Lifecycle.Event.ON_DESTROY -> {
                        playingTarget = null
                        mTargets.clear();
//                        mRecyclerView.removeCallbacks()
                    }


                }
            }
        })

    }

}