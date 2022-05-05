@file:Suppress("UNCHECKED_CAST")
package com.example.daggerhiltplayground.repository

import com.example.daggerhiltplayground.database.online.APIRequests
import com.example.daggerhiltplayground.network.Network
import com.example.daggerhiltplayground.network.NetworkResult
import com.example.daggerhiltplayground.pojo.episode.Episode
import com.example.daggerhiltplayground.pojo.character.Character
import com.example.daggerhiltplayground.util.ResponseStatus
import javax.inject.Inject


class EpisodeRepo @Inject constructor(private val episodeAPIRequests : APIRequests){

    suspend fun singleEpisodeCAll(episodeNumber: String): NetworkResult<Episode> {
        val response =
            Network.responseManager(episodeAPIRequests.getEpisode(episodeNumber)) as NetworkResult<Episode>
        return when (response.status) {
            ResponseStatus.SUCCESS -> {
                NetworkResult.Success(response.data)
            }
            ResponseStatus.ERROR -> {
                NetworkResult.Failure("Getting all character request has been failed")
            }
            ResponseStatus.CANCELLED -> {
                NetworkResult.Cancelled("Getting all character request has been cancelled")
            }
            ResponseStatus.LOADING -> {
                NetworkResult.Loading()
            }
        }
    }

    suspend fun gatMultipleCharacters(charactersIDs: List<Int>) : NetworkResult<List<Character>>{
        val response =
            Network.responseManager(episodeAPIRequests.getMultipleCharacters(charactersIDs)) as NetworkResult<List<Character>>
        return when (response.status) {
            ResponseStatus.SUCCESS -> {
                NetworkResult.Success(response.data)
            }
            ResponseStatus.ERROR -> {
                NetworkResult.Failure("Getting all character request has been failed")
            }
            ResponseStatus.CANCELLED -> {
                NetworkResult.Cancelled("Getting all character request has been cancelled")
            }
            ResponseStatus.LOADING -> {
                NetworkResult.Loading()
            }
        }
    }


}