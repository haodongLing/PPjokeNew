package io.github.haodongling.ppjokenew

import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import io.github.haodongling.lib.common.core.BaseActivity
import io.github.haodongling.lib.common.ext.toast

/**
 * Author: tangyuan
 * Time : 2021/11/22
 * Description:
 */
class MainActivity : BaseActivity() {
    var exitTime = 0L
    override fun getLayoutResId(): Int {
      return  R.layout.activity_main
    }

    override fun initView() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val nav = Navigation.findNavController(this@MainActivity, R.id.host_fragment)
                if (nav.currentDestination != null && nav.currentDestination!!.id != R.id.mainfragment) {
                    //如果当前界面不是主页，那么直接调用返回即可
                    nav.navigateUp()
                } else {
                    //是主页
                    if (System.currentTimeMillis() - exitTime > 2000) {
                        toast("再按一次退出程序")
                        exitTime = System.currentTimeMillis()
                    } else {
                        finish()
                    }
                }
            }
        })
    }

    override fun initData() {
    }
}