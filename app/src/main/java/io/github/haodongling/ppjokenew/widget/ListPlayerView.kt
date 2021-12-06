package io.github.haodongling.ppjokenew.widget

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.*
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.annotation.NonNull
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerControlView
import io.github.haodongling.lib.common.util.PixUtils
import io.github.haodongling.ppjokenew.R
import io.github.haodongling.ppjokenew.exoplayer.IPlayTarget
import io.github.haodongling.ppjokenew.exoplayer.PageListPlay
import io.github.haodongling.ppjokenew.exoplayer.PageListPlayManager

/**
 * Author: tangyuan
 * Time : 2021/12/5
 * Description:
 */
class ListPlayerView : FrameLayout, View.OnClickListener, IPlayTarget, PlayerControlView.VisibilityListener,
    Player.EventListener {
    lateinit var bufferView: View;
    lateinit var cover: PPImageView
    lateinit var blur: PPImageView
    protected lateinit var playBtn: ImageView
    protected lateinit var mCategory: String
    protected lateinit var mVideoUrl: String
    protected var mWidthPx: Int = 0
    protected var mHeightPx: Int = 0

    constructor(@NonNull context: Context) : super(context, null)
    constructor(@NonNull context: Context, attrs: AttributeSet) : super(context, attrs, 0)
    constructor(@NonNull context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        LayoutInflater.from(context).inflate(R.layout.layout_player_view, this, true)
        bufferView = findViewById(R.id.buffer_view)
        cover = findViewById(R.id.cover)
        blur = findViewById(R.id.blur_background)
        playBtn = findViewById(R.id.play_btn)
        playBtn.setOnClickListener(this@ListPlayerView)


    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //点击该区域时 我们诸主动让视频控制器显示出来
        val pageListPlay: PageListPlay = PageListPlayManager.get(mCategory)
        pageListPlay.controlView?.show()
        return true

    }

    fun bindData(category: String, widthPx: Int, heightPx: Int, coverUrl: String, videoUrl: String) {
        mCategory = category
        mVideoUrl = videoUrl
        mWidthPx = widthPx
        mHeightPx = heightPx
        cover.setImageUrl(coverUrl)

        //如果该视频的宽度小于高度,则高斯模糊背景图显示出来
        if (widthPx < heightPx) {
            PPImageView.setBlurImageUrl(blur, coverUrl, 10)
            blur.visibility = VISIBLE
        } else {
            blur.visibility = INVISIBLE
        }
        setSize(widthPx, heightPx)
    }

    protected fun setSize(widthPx: Int, heightPx: Int) {
        var maxWidth = PixUtils.getScreenWidth();
        var maxHeight = maxWidth;
        var layoutWith = maxWidth
        var layoutHeight = 0
        var coverWidth = 0;
        var coverHeight = 0
        if (widthPx >= heightPx) {
            coverWidth = maxWidth
            layoutHeight = (heightPx / (widthPx * 1.0f / maxWidth)).toInt()
            coverHeight = layoutHeight
        } else {
            layoutHeight = maxHeight
            coverHeight = maxHeight
        }
        layoutParams.width = layoutWith
        layoutParams.height = layoutHeight

        val blurParams = blur.layoutParams
        blurParams.width = layoutWith
        blurParams.height = layoutHeight

        (playBtn.layoutParams as FrameLayout.LayoutParams).gravity = Gravity.CENTER

    }


    override fun onClick(v: View) {
    }

    override fun getOwner(): ViewGroup {
        return this@ListPlayerView
    }

    override fun onActive() {
        val pageListPlay: PageListPlay = PageListPlayManager.get(mCategory)
        val playerView = pageListPlay.playerView
        val controlView = pageListPlay.controlView
        val exoPlayer = pageListPlay.exoPlayer
        if (playerView == null) {
            return
        }
        pageListPlay.switchPlayerView(playerView, true)
        var ctrlParent = playerView.parent
        if (ctrlParent !== this) {
            //把视频控制器 添加到ItemView的容器上
            if (ctrlParent != null) {
                (ctrlParent as ViewGroup).removeView(controlView)
            }
            val params = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            params.gravity = Gravity.BOTTOM
            this.addView(controlView, params)
        }

        //如果是同一个视频资源,则不需要从重新创建mediaSource。
        //但需要onPlayerStateChanged 否则不会触发onPlayerStateChanged()

        //如果是同一个视频资源,则不需要从重新创建mediaSource。
        //但需要onPlayerStateChanged 否则不会触发onPlayerStateChanged()
        if (TextUtils.equals(pageListPlay.playUrl, mVideoUrl)) {
            onPlayerStateChanged(true, Player.STATE_READY)
        } else {
            val mediaSource = PageListPlayManager.createMediaSource(mVideoUrl)
            exoPlayer!!.prepare(mediaSource)
            exoPlayer!!.repeatMode = Player.REPEAT_MODE_ONE
            pageListPlay.playUrl = mVideoUrl
        }
        controlView!!.show()
        controlView!!.setVisibilityListener(this)
        exoPlayer!!.addListener(this)
        exoPlayer!!.playWhenReady = true

    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mIsPlaying = false
        bufferView.visibility = GONE
        cover.visibility = VISIBLE
        playBtn.visibility = VISIBLE
        playBtn.setImageResource(R.drawable.icon_video_play)
    }

    override fun inActive() {
        TODO("Not yet implemented")
    }

    protected var mIsPlaying = false
    override fun isPlaying(): Boolean {
        return mIsPlaying
    }

    override fun onVisibilityChange(visibility: Int) {
        playBtn.visibility = visibility
        playBtn.setImageResource(if (isPlaying()) R.drawable.icon_video_pause else R.drawable.icon_video_play)
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        //监听视频播放的状态


        //监听视频播放的状态
        val pageListPlay = PageListPlayManager.get(mCategory)
        val exoPlayer = pageListPlay.exoPlayer
        if (playbackState == Player.STATE_READY && exoPlayer!!.bufferedPosition != 0L && playWhenReady) {
            cover.visibility = GONE
            bufferView.visibility = GONE
        } else if (playbackState == Player.STATE_BUFFERING) {
            bufferView.visibility = VISIBLE
        }
        mIsPlaying = playbackState == Player.STATE_READY && exoPlayer!!.bufferedPosition != 0L && playWhenReady
        playBtn.setImageResource(if (mIsPlaying) R.drawable.icon_video_pause else R.drawable.icon_video_play)
    }

    fun getPlayerController(): View {
        val listPlay = PageListPlayManager.get(mCategory)
        return listPlay.controlView!!
    }
}