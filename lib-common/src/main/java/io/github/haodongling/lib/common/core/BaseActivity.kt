package io.github.haodongling.lib.common.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import io.github.haodongling.lib.common.network.manager.NetState
import io.github.haodongling.lib.common.network.manager.NetworkStateManager
import qiu.niorgai.StatusBarCompat

/**
 * Author: tangyuan
 * Time : 2021/8/14
 * Description:
 */
abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        beforeSetContentView(savedInstanceState)
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        setStatusBar()
        initView()
        initData()
        NetworkStateManager.instance.mNetworkState.observe(this, Observer {
            onNetworkStateChanged(it)
        })
    }

    abstract fun getLayoutResId(): Int
    abstract fun initView()
    abstract fun initData()
    open fun beforeSetContentView(savedInstanceState: Bundle?){


    }
    open fun setStatusBar(){
        //透明状态栏
        StatusBarCompat.translucentStatusBar(this@BaseActivity);
    }
    /**
     * 网络变化监听 子类重写
     */
    open fun onNetworkStateChanged(netState: NetState) {}



}