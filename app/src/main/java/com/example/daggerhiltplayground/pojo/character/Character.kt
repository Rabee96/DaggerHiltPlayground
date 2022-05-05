package com.example.daggerhiltplayground.pojo.character

import androidx.room.*

@Entity(tableName = "Character_Table", indices = [Index(value = ["id"], unique = true, orders = [Index.Order.ASC])])
data class Character(
    val created: String,
    val episode: List<String>,
    val gender: String,
    @PrimaryKey val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)