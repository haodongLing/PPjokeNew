package io.github.haodongling.ppjokenew.api

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import io.github.haodongling.lib.common.App
import io.github.haodongling.lib.common.network.api.BaseRetrofitClient
import io.github.haodongling.lib.common.util.NetWorkUtils
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import java.io.File


/**
 * Created by luyao
 * on 2018/3/13 15:45
 */
object RetrofitClient : BaseRetrofitClient() {
    val userService=getService(UserService::class.java)
    val FeedService =getService(FeedService::class.java)


    private val cookieJar by lazy { PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(App.CONTEXT)) }

    override fun handleBuilder(builder: OkHttpClient.Builder) {

        val httpCacheDirectory = File(App.APP.applicationContext.cacheDir, "responses")
        val cacheSize = 10 * 1024 * 1024L // 10 MiB
        val cache = Cache(httpCacheDirectory, cacheSize)
        builder.cache(cache)
                .cookieJar(cookieJar)
                .addInterceptor { chain ->
                    var request = chain.request()
                    if (!NetWorkUtils.isNetworkAvailable(App.APP.applicationContext)) {
                        request = request.newBuilder()
                                 .cacheControl(CacheControl.FORCE_CACHE)
                                .build()
                    }
                    val response = chain.proceed(request)
                    if (!NetWorkUtils.isNetworkAvailable(App.APP.applicationContext)) {
                        val maxAge = 60 * 60
                        response.newBuilder()
                                .removeHeader("Pragma")
                                .header("Cache-Control", "public, max-age=$maxAge")
                                .build()
                    } else {
                        val maxStale = 60 * 60 * 24 * 7 // tolerate 4-weeks stale
                        response.newBuilder()
                                .removeHeader("Pragma")
                                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                                .build()
                    }
                    response
                }
    }
    val mCookieJar:PersistentCookieJar by lazy {
        PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(App.APP.applicationContext))
    }

}