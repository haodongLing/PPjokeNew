package io.github.haodongling.ppjokenew.ui

import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import io.github.haodongling.lib.common.core.BaseVMFragment
import io.github.haodongling.ppjokenew.R
import io.github.haodongling.ppjokenew.databinding.FragmentMainBinding
import io.github.haodongling.ppjokenew.ui.find.FindFragment
import io.github.haodongling.ppjokenew.ui.home.HomeFragment
import io.github.haodongling.ppjokenew.ui.mine.MineFragment
import io.github.haodongling.ppjokenew.ui.sofa.SofaFragment
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * Author: tangyuan
 * Time : 2021/11/22
 * Description:
 */
class MainFragment : BaseVMFragment<FragmentMainBinding>(R.layout.fragment_main),NavigationBarView.OnItemSelectedListener{

    override fun setVariable() {
    }

    override fun initView() {
        initViewPager()
        bottom_nav.setOnItemSelectedListener(this)
    }

    override fun initData() {
    }

    override fun startObserve() {
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.tab_home->mainViewpager.setCurrentItem(0,false)
            R.id.tab_sofa->mainViewpager.setCurrentItem(1,false)
            R.id.tab_find->mainViewpager.setCurrentItem(2,false)
            R.id.tab_mine->mainViewpager.setCurrentItem(3,false)
        }

        return true
    }
    private fun initViewPager(){
        mainViewpager.run {
            isUserInputEnabled=false;
            offscreenPageLimit=4
            //设置适配器
            adapter = object : FragmentStateAdapter(this@MainFragment) {
                override fun createFragment(position: Int): Fragment {
                    when (position) {
                        0 -> {
                            return HomeFragment.create("all")
                        }
                        1 -> {
                            return SofaFragment()
                        }
                        2 -> {
                            return FindFragment()
                        }
                        3 -> {
                            return MineFragment()
                        }
                        else -> {
                            return HomeFragment()
                        }
                    }
                }
                override fun getItemCount() = 4
            }
        }

    }

}