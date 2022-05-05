package com.example.daggerhiltplayground.pojo.episode

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Episode_Table")
data class Episode(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    @PrimaryKey val id: Int,
    val name: String,
    val url: String
){

    fun Episode.toEntity(){

    }
}