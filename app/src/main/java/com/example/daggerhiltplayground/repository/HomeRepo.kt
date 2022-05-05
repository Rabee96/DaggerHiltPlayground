@file:Suppress("UNCHECKED_CAST")

package com.example.daggerhiltplayground.repository

import com.example.daggerhiltplayground.database.online.APIRequests
import com.example.daggerhiltplayground.network.Network
import com.example.daggerhiltplayground.network.NetworkResult
import com.example.daggerhiltplayground.pojo.charactersList.Characters
import com.example.daggerhiltplayground.util.ResponseStatus.*
import javax.inject.Inject

class HomeRepo @Inject constructor(private val homeAPIRequests: APIRequests) {

    suspend fun charactersCAll(page: Int): NetworkResult<Characters> {
        val response =
            Network.responseManager(homeAPIRequests.getAllCharacters(page)) as NetworkResult<Characters>
        return when (response.status) {
            SUCCESS -> {
                NetworkResult.Success(response.data)
            }
            ERROR -> {
                NetworkResult.Failure("Getting all character request has been failed")
            }
            CANCELLED -> {
                NetworkResult.Cancelled("Getting all character request has been cancelled")
            }
            LOADING -> {
                NetworkResult.Loading()
            }
        }
    }
}