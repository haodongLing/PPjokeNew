package io.github.haodongling.ppjokenew.utils

import android.text.TextUtils
import com.google.gson.Gson
import com.tencent.mmkv.MMKV
import io.github.haodongling.ppjokenew.model.User

/**
 * Author: tangyuan
 * Time : 2021/12/2
 * Description:
 */
object CacheUtil {
    private val mUser: User? by lazy {
        getUser()
    }

    fun getUser(): User? {
        val kv = MMKV.mmkvWithID("app")
        val userStr = kv.decodeString("user")
        return if (TextUtils.isEmpty(userStr)) {
            null
        } else {
            Gson().fromJson(userStr, User::class.java)
        }
    }

    /**
     * 设置账户信息
     */
    fun setUser(userResponse: User?) {
        val kv = MMKV.mmkvWithID("app")
        if (userResponse == null) {
            kv.encode("user", "")
            setIsLogin(false)
        } else {
            kv.encode("user", Gson().toJson(userResponse))
            setIsLogin(true)
        }

    }

    /**
     * 是否已经登录
     */
    fun isLogin(): Boolean {
        val kv = MMKV.mmkvWithID("app")
        return kv.decodeBool("login", false)
    }

    /**
     * 设置是否已经登录
     */
    fun setIsLogin(isLogin: Boolean) {
        val kv = MMKV.mmkvWithID("app")
        kv.encode("login", isLogin)
    }

    /**
     * 是否是第一次登陆
     */
    fun isFirst(): Boolean {
        val kv = MMKV.mmkvWithID("app")
        return kv.decodeBool("first", true)
    }

    /**
     * 是否是第一次登陆
     */
    fun setFirst(first: Boolean): Boolean {
        val kv = MMKV.mmkvWithID("app")
        return kv.encode("first", first)
    }

    fun getUserId(): Long {
        return if (mUser == null) {
            0
        } else {
            mUser!!.userId
        }
    }


}