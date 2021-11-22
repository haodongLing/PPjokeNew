package io.github.haodongling.lib.common.model.repository

import io.github.haodongling.lib.common.network.DTOResult
import io.github.haodongling.lib.common.network.HttpCode
import io.github.haodongling.lib.common.network.WanResponse

open class BaseRepository {

    suspend fun <T : Any> apiCall(call: suspend () -> WanResponse<T>): WanResponse<T> {
        return call.invoke()
    }

    suspend fun <T : Any> safeApiCall(call: suspend () -> DTOResult<T>, errorMessage: String): DTOResult<T> {
        return try {
            call()
        } catch (e: Exception) {
            // An exception was thrown when calling the API so we're converting this to an IOException
            DTOResult.Error(HttpCode(-1, errorMessage))
        }
    }

}