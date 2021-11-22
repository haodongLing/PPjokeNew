package io.github.haodongling.lib.common.network.manager

import com.kunminx.architecture.ui.callback.UnPeekLiveData

/**
 * Author: tangyuan
 * Time : 2021/11/22
 * Description:
 */
class NetworkStateManager private constructor(){
    val mNetworkState=UnPeekLiveData<NetState>()
    companion object {
        val instance: NetworkStateManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkStateManager()
        }
    }
}