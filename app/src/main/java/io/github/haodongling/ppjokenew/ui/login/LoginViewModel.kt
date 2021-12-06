package io.github.haodongling.ppjokenew.ui.login

import com.tencent.connect.UserInfo
import com.tencent.connect.auth.QQToken
import com.tencent.tauth.IUiListener
import com.tencent.tauth.Tencent
import io.github.haodongling.lib.common.App
import io.github.haodongling.lib.common.core.BaseActivity
import io.github.haodongling.lib.common.core.BaseViewModel

/**
 * Author: tangyuan
 * Time : 2021/11/29
 * Description:
 */
class LoginViewModel : BaseViewModel() {
    private val tencent: Tencent by lazy {
        Tencent.createInstance("101794421", App.CONTEXT)
    }

    fun login(activity: BaseActivity, loginListener: IUiListener) {
        tencent.login(activity, "all", loginListener)
    }

    fun getUserInfo(qqToken: QQToken, expires_time: Long, openId: String, uiListener: IUiListener) {
        val userInfo = UserInfo(App.CONTEXT, qqToken)
        userInfo.getUserInfo(uiListener)

    }

    fun save(nickName: String, avatar: String, openId: String, expires_time: Long) {
        launchOnUI {

        }

    }

}