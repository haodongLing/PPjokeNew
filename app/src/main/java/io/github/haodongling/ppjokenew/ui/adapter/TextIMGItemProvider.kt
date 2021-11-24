package io.github.haodongling.ppjokenew.ui.adapter

import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import io.github.haodongling.ppjokenew.model.Feed

/**
 * Author: tangyuan
 * Time : 2021/11/24
 * Description:
 */
class TextIMGItemProvider() : BaseItemProvider<Feed>() {
    override fun convert(helper: BaseViewHolder, item: Feed) {

    }

    override val itemViewType: Int
        get() = Feed.TYPE_IMAGE_TEXT
    override val layoutId: Int
        get() = Feed.TYPE_IMAGE_TEXT
}