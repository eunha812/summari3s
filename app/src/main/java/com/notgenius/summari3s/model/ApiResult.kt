package com.notgenius.summari3s.model

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()

    data class Error(val code: Int, val message: String?) : ApiResult<Nothing>()

    data class Exception(val e: Throwable) : ApiResult<Nothing>()
}
