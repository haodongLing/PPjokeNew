package io.github.haodongling.ppjokenew.ui.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import io.github.haodongling.ppjokenew.BR
import io.github.haodongling.ppjokenew.R
import io.github.haodongling.ppjokenew.databinding.LayoutFeedTypeImageBinding
import io.github.haodongling.ppjokenew.exoplayer.PageListPlayDetector
import io.github.haodongling.ppjokenew.model.Feed

/**
 * Author: tangyuan
 * Time : 2021/11/24
 * Description:
 */
class TextIMGItemProvider : BaseItemProvider<Feed>() {
    override fun convert(helper: BaseViewHolder, item: Feed) {

    }

    override val itemViewType: Int
        get() = Feed.TYPE_IMAGE_TEXT
    override val layoutId: Int
        get() = R.layout.layout_feed_type_image

    override fun onViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<LayoutFeedTypeImageBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, item: Feed, payloads: List<Any>) {
        val binding=DataBindingUtil.getBinding<LayoutFeedTypeImageBinding>(helper.itemView)
        binding?.let {
            it.setVariable(io.github.haodongling.ppjokenew.BR.feed,item)
            it.setVariable(BR.lifeCycleOwner,context)
            binding.feedImage.bindData(item.width,item.height,16,item.cover)
        }
    }
}