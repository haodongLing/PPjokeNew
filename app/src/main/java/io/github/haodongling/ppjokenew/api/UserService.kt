package io.github.haodongling.ppjokenew.api

import io.github.haodongling.lib.common.network.WanResponse
import io.github.haodongling.ppjokenew.model.User
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Author: tangyuan
 * Time : 2021/11/29
 * Description:
 */
interface UserService {
    @GET("/user/insert")
    suspend fun insertUser(
        @Query("name") name: String,
        @Query("avatar") avatar: String,
        @Query("qqOpenId") openId: String,
        @Query("expires_time") expires_time: String
    ): WanResponse<User>
}