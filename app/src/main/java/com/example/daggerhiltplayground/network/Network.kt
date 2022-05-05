package com.example.daggerhiltplayground.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.daggerhiltplayground.BuildConfig.DEFAULT_LOG_FILLTER
import com.google.gson.Gson
import kotlinx.coroutines.CancellationException
import retrofit2.Response

object Network {

    fun isInternetAvailable(context: Context): Boolean {
        val result : Boolean
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return result
    }

    fun responseManager(response : Response<*>): NetworkResult<Any> {
        try {
            if (response.isSuccessful) {
                when (response.code()) {
                    200 -> {
                        Log.e("API $DEFAULT_LOG_FILLTER", "Enter 200")
                        return NetworkResult.Success(response.body())
                    }
                    401 -> {
                        Log.e("API $DEFAULT_LOG_FILLTER", "Enter 401")
                        return NetworkResult.Failure(response.message())
                    }
                    404 -> {
                        Log.e("API $DEFAULT_LOG_FILLTER", "Enter 404")
                        return NetworkResult.Failure(response.message())
                    }
                }
            } else {
                throw (Exception("Error in getting data from the network"))
            }
        } catch (e: CancellationException) {
            Log.e("E${"-API $DEFAULT_LOG_FILLTER"}", "Error : ${e.message} and cause : ${e.cause}")
            return NetworkResult.Failure(e.message)
        }
        return NetworkResult.Failure("Unexpected Error occurred in API call")
    }

}