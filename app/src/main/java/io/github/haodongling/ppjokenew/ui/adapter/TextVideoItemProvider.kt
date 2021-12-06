package io.github.haodongling.ppjokenew.ui.adapter

import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.bind
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import io.github.haodongling.ppjokenew.BR
import io.github.haodongling.ppjokenew.R
import io.github.haodongling.ppjokenew.databinding.LayoutFeedTypeVideoBinding
import io.github.haodongling.ppjokenew.exoplayer.PageListPlayDetector
import io.github.haodongling.ppjokenew.model.Feed

/**
 * Author: tangyuan
 * Time : 2021/12/5
 * Description:
 */
class TextVideoItemProvider(category: String, pageListPlayDetector: PageListPlayDetector) : BaseItemProvider<Feed>() {
    override val itemViewType: Int
        get() = Feed.TYPE_VIDEO
    override val layoutId: Int
        get() = R.layout.layout_feed_type_video
    private lateinit var mCategory: String

    init {
        var mCategory: String = category
    }

    override fun onViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<LayoutFeedTypeVideoBinding>(viewHolder.itemView)
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder) {
        super.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder) {
        super.onViewDetachedFromWindow(holder)
    }


    override fun convert(helper: BaseViewHolder, item: Feed) {
        val binding = DataBindingUtil.getBinding<LayoutFeedTypeVideoBinding>(helper.itemView)
        binding?.let {
            it.setVariable(io.github.haodongling.ppjokenew.BR.feed, item)
            it.setVariable(BR.lifeCycleOwner, context)
            it.listPlayerView.bindData(mCategory, item.width, item.height, item.cover, item.url)
        }

    }

}