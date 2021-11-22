package io.github.haodongling.lib.common.network


sealed class DTOResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : DTOResult<T>()
    data class Error(val httpCode: HttpCode) : DTOResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=${httpCode.errMessage}]"
        }
    }
}