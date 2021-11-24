package io.github.haodongling.ppjokenew.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import io.github.haodongling.lib.common.core.BaseVMFragment
import io.github.haodongling.ppjokenew.R
import io.github.haodongling.ppjokenew.databinding.FragmentHomeBinding

/**
 * Author: tangyuan
 * Time : 2021/11/24
 * Description:
 */
class HomeFragment : BaseVMFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    lateinit var feedType: String
    companion object {
       fun create( feedType:String): HomeFragment{
           val bundle=Bundle()
           val fragment:HomeFragment= HomeFragment()
           bundle.putString("feedType",feedType)
           fragment.arguments=bundle;
           return fragment

       }

    }

    override fun setVariable() {
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun startObserve() {
    }
}