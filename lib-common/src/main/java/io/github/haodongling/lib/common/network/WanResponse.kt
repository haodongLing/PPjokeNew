package io.github.haodongling.lib.common.network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

data class WanResponse<out T>(val success: Boolean, val status: Int, var message: String? = null, val body: T)

suspend fun <T : Any> WanResponse<T>.doSuccess(successBlock: (suspend CoroutineScope.(T) -> Unit)? = null): WanResponse<T> {
    return coroutineScope {
        if (success) successBlock?.invoke(this, this@doSuccess.body)
        this@doSuccess
    }

}

suspend fun <T : Any> WanResponse<T>.doError(errorBlock: (suspend CoroutineScope.(HttpCode) -> Unit)? = null): WanResponse<T> {
    return coroutineScope {
        if (!success) errorBlock?.invoke(this, HttpCode(this@doError.status, this@doError.message ?: ""))
        this@doError
    }
}

