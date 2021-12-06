package io.github.haodongling.lib.common.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

/**
 * Author: tangyuan
 * Time : 2021/7/20
 * Description:
 */
open class BaseViewModel : ViewModel() {


    fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {

        viewModelScope.launch { block() }

    }
    suspend fun <T> launchOnIO(block: suspend CoroutineScope.() -> T) {
        withContext(Dispatchers.IO) {
            block
        }
    }
    fun <T> BaseViewModel.launch(
        block: () -> T,
        success: (T) -> Unit,
        error: (Throwable) -> Unit = {}
    ) {
        viewModelScope.launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    block()
                }
            }.onSuccess {
                success(it)
            }.onFailure {
                error(it)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}