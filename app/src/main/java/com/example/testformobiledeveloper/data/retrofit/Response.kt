package com.example.testformobiledeveloper.data.retrofit

import androidx.annotation.CheckResult

interface IResponse

sealed class Response<T : IResponse> {
    data class Success<T : IResponse>(val value: T) : Response<T>()
    data class Failure<T : IResponse>(val value: ErrorResponse) : Response<T>()

    inline fun <X> fold(
        success: (T) -> X,
        failure: (ErrorResponse) -> X
    ): X = when (this) {
        is Success -> success(value)
        is Failure -> failure(value)
    }

    @CheckResult
    fun onFailure(block: (ErrorResponse) -> Unit): Response<T> = when (this) {
        is Success -> this
        is Failure -> {
            block(this.value)
            this
        }
    }

    @CheckResult
    fun successOrElse(acc: T): T = when (this) {
        is Success -> value
        is Failure -> acc
    }

    @CheckResult
    fun successOrNull(): T? = when (this) {
        is Success -> value
        is Failure -> null
    }
}