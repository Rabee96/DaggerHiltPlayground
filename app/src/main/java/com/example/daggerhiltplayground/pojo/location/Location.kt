package com.example.daggerhiltplayground.pojo.location

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.daggerhiltplayground.util.PojoTypeConverter

@Entity(tableName = "Location_Table")
data class Location(
    val created: String,
    val dimension: String,
    @PrimaryKey val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
)