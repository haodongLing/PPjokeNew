package io.github.haodongling.ppjokenew.api

import io.github.haodongling.lib.common.network.WanResponse
import io.github.haodongling.ppjokenew.model.Feed
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Author: tangyuan
 * Time : 2021/12/2
 * Description:
 */
interface FeedService {
    @GET("/feeds/queryHotFeedsList")
    suspend fun getHotFeedList(
        @Query("feedType") mFeedType: String,
        @Query("userId") userId: Any,
        @Query("feedId") feedId: Int,
        @Query("pageCount") count: Int
    ):WanResponse<ArrayList<Feed>>
}