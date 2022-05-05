package com.example.daggerhiltplayground.network

import com.example.daggerhiltplayground.util.ResponseStatus
import com.example.daggerhiltplayground.util.ResponseStatus.*

sealed class NetworkResult <T> (var status: ResponseStatus = LOADING, var data : T? = null, val error: String? = null){
    class Loading <T> : NetworkResult<T>(LOADING,null,null)
    class Success <T>(data: T?) : NetworkResult<T>(SUCCESS, data,null)
    class Failure <T>(message: String?) : NetworkResult<T>(ERROR,null,message)
    class Cancelled <T>(message: String?) : NetworkResult<T>(CANCELLED,null,message)
}
