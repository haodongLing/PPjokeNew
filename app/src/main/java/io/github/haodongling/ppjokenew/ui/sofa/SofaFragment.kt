package io.github.haodongling.ppjokenew.ui.sofa

import androidx.fragment.app.Fragment
import io.github.haodongling.lib.common.core.BaseVMFragment
import io.github.haodongling.lib.common.ext.bindViewPager2
import io.github.haodongling.lib.common.ext.init
import io.github.haodongling.ppjokenew.R
import io.github.haodongling.ppjokenew.databinding.FragmentViewpagerBinding
import io.github.haodongling.ppjokenew.ui.home.HomeFragment
import kotlinx.android.synthetic.main.include_viewpager.*

/**
 * Author: tangyuan
 * Time : 2021/11/24
 * Description:
 */
class SofaFragment : BaseVMFragment<FragmentViewpagerBinding>(R.layout.fragment_viewpager) {
    private lateinit var fragments: ArrayList<Fragment>
    private lateinit var mDataList: ArrayList<String>

    override fun initView() {
        fragments=arrayListOf(
            HomeFragment.create("pics"), HomeFragment.create("video"), HomeFragment.create("text")
        )
        mDataList=arrayListOf("图片", "视频", "文本")

        view_pager.init(this, fragments)
        magic_indicator.bindViewPager2(view_pager, mDataList)

    }

    override fun initData() {
    }

    override fun startObserve() {
    }

    override fun setVariable() {

    }
}