package io.github.haodongling.lib.common.network.intercepter

import io.github.haodongling.lib.common.util.FFLog
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Author: tangyuan
 * Time : 2021/8/17
 * Description:
 */
class ResponseInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request())
        FFLog.d("reponse-->$response")
        //        try {
//            if (response.body()!=null){
//                BufferedSource source = response.body().source();
//                Buffer buffer = source.buffer();
//                Charset UTF8 = Charset.forName("UTF-8");
//                String bodyStr = buffer.clone().readString(UTF8);
//                JSONObject jsonObject = new JSONObject(bodyStr);
//                FFLog.i("errorCode-->"+jsonObject.optInt("errorCode    ")+jsonObject.optString("errorMsg"," "));
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        return response
    }
}