package io.github.haodongling.ppjokenew

import io.github.haodongling.lib.common.App

/**
 * Author: tangyuan
 * Time : 2021/11/25
 * Description:
 */
class MyApplication : App() {
    companion object {
        lateinit var appViewModel: AppViewModel
    }

    override fun onCreate() {
        super.onCreate()
       appViewModel = getAppViewModelProvider().get(AppViewModel::class.java)
    }
}
//val appViewModel by lazy { MyApplication.appViewModel }