package io.github.haodongling.ppjokenew.exoplayer

import android.view.LayoutInflater
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerControlView
import com.google.android.exoplayer2.ui.PlayerView
import io.github.haodongling.lib.common.App
import io.github.haodongling.ppjokenew.R

/**
 * Author: tangyuan
 * Time : 2021/11/26
 * Description:
 */
class PageListPlay {
    var exoPlayer: SimpleExoPlayer? = ExoPlayerFactory.newSimpleInstance(
        App.CONTEXT, DefaultRenderersFactory(App.CONTEXT), DefaultTrackSelector(), DefaultLoadControl()
    )
    var playerView: PlayerView? =
        LayoutInflater.from(App.CONTEXT).inflate(R.layout.layout_exo_player_view, null, false) as PlayerView
    var controlView: PlayerControlView? = LayoutInflater.from(App.CONTEXT)
        .inflate(R.layout.layout_exo_player_contorller_view, null, false) as PlayerControlView
    lateinit var playUrl: String

    init {
        playerView?.player = exoPlayer
        controlView?.player = exoPlayer

    }

    fun release() {
        if (exoPlayer != null) {
            exoPlayer?.playWhenReady = false
            exoPlayer?.stop(true)
            exoPlayer?.release()
            exoPlayer = null
        }
        if (playerView != null) {
            playerView?.player = null
            playerView = null
        }

        if (controlView != null) {
            controlView?.player = null
            controlView?.setVisibilityListener(null)
            controlView = null
        }


    }

    /**
     * 切换与播放器exoplayer 绑定的exoplayerView。用于页面切换视频无缝续播的场景
     *
     * @param newPlayerView
     * @param attach
     */
    fun switchPlayerView(newPlayerView: PlayerView, attach: Boolean) {
        playerView?.player = if (attach) null else exoPlayer
        newPlayerView.player = if (attach) exoPlayer else null
    }


}