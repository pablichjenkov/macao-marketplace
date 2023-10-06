package com.pablichj.incubator.amadeus.common

import AmadeusError

sealed class CallResult<out T> {
    class Error(val error: AmadeusError) : CallResult<Nothing>()
    class Success<T>(val responseBody: T) : CallResult<T>()
}
