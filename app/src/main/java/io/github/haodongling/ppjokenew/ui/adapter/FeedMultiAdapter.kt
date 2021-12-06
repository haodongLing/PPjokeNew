package io.github.haodongling.ppjokenew.ui.adapter

import com.chad.library.adapter.base.BaseProviderMultiAdapter
import io.github.haodongling.ppjokenew.exoplayer.PageListPlayDetector
import io.github.haodongling.ppjokenew.model.Feed

/**
 * Author: tangyuan
 * Time : 2021/12/6
 * Description:
 */
class FeedMultiAdapter() : BaseProviderMultiAdapter<Feed>() {
    override fun getItemType(data: List<Feed>, position: Int): Int {
        return data[position].itemType
    }

    constructor(mCategory: String, pageListPlayDetector: PageListPlayDetector) : this() {
        addItemProvider(TextIMGItemProvider())
        addItemProvider(TextVideoItemProvider(mCategory, pageListPlayDetector))
    }
}