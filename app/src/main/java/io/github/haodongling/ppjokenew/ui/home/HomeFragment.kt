package io.github.haodongling.ppjokenew.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import io.github.haodongling.lib.common.core.BaseVMFragment
import io.github.haodongling.ppjokenew.R
import io.github.haodongling.ppjokenew.databinding.FragmentHomeBinding

/**
 * Author: tangyuan
 * Time : 2021/11/24
 * Description:
 */
class HomeFragment : BaseVMFragment<FragmentHomeBinding>(R.layout.fragment_home), OnRefreshListener,
    OnLoadMoreListener {
    private val homeViewModel  by lazy {
        getActivityScopeViewModel(HomeViewModel::class.java)
    }

    companion object {
        fun create(feedType: String): HomeFragment {
            val bundle = Bundle()
            val fragment: HomeFragment = HomeFragment()
            bundle.putString("feedType", feedType)
            fragment.arguments = bundle;
            return fragment

        }

    }

    override fun setVariable() {
        homeViewModel.mFeedType = if (arguments == null) {
            "all"
        } else {
            requireArguments().getString("feedType")!!
        }
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun startObserve() {
        homeViewModel.feedListState.observe(viewLifecycleOwner) {
            it.showSuccess?.let {

            }
            it.showError?.let {

            }
            it.showLoading?.let {

            }

        }

    }

    override fun onRefresh(refreshLayout: RefreshLayout) {

    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {

    }
}