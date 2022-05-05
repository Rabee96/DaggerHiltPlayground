package com.example.daggerhiltplayground.database.online

import com.example.daggerhiltplayground.pojo.character.Character
import com.example.daggerhiltplayground.pojo.charactersList.Characters
import com.example.daggerhiltplayground.pojo.episode.Episode
import com.example.daggerhiltplayground.pojo.episodesList.EpisodesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIRequests {

    //================================== === All Character API Calls === ============================================
    @GET("character")
    suspend fun getAllCharacters(@Query("page") page: Int) : Response<Characters>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int) : Response<Character>

    @GET("character/{ids}")
    suspend fun getMultipleCharacters(@Path("ids") listIds: List<Int>) : Response<List<Character>>

    //================================== === All Location API Calls === ============================================

    //================================== === All Episode API Calls === ============================================
    @GET("episode")
    suspend fun getAllEpisodes(@Query("page") page: Int) : Response<EpisodesList>

    @GET("episode/{id}")
    suspend fun getEpisode(@Path("id") episodeId: String) : Response<Episode>

    @GET("episode/{ids}")
    suspend fun getMultipleEpisodes(@Path("ids") listEpisodesIds: List<Int>) : Response<List<Episode>>

}