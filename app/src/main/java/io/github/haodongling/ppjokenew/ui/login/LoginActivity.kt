package io.github.haodongling.ppjokenew.ui.login

import android.view.View
import com.tencent.tauth.Tencent
import io.github.haodongling.lib.common.core.BaseVMActivity
import io.github.haodongling.ppjokenew.R
import io.github.haodongling.ppjokenew.databinding.ActivityLayoutLoginBinding

/**
 * Author: tangyuan
 * Time : 2021/11/29
 * Description:
 */
class LoginActivity : BaseVMActivity<ActivityLayoutLoginBinding>() {
    lateinit var tencent:Tencent
    override fun getLayoutId(): Int {
        return R.layout.activity_layout_login
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