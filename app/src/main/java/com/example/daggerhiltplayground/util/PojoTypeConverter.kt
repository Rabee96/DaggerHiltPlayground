package com.example.daggerhiltplayground.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.daggerhiltplayground.pojo.character.Location
import com.example.daggerhiltplayground.pojo.character.Origin
import com.example.daggerhiltplayground.pojo.episode.Episode
import com.google.gson.Gson
import javax.inject.Inject

@ProvidedTypeConverter
class PojoTypeConverter @Inject constructor(private val gson: Gson) {

    @TypeConverter
    fun toLocation(json: String?): Location {
        return gson.fromJson(json, Location::class.java)
    }

    @TypeConverter
    fun toOrigin(json: String?): Origin {
        return gson.fromJson(json, Origin::class.java)
    }

    @TypeConverter
    fun toEpisode(json: String?): Episode {
        return gson.fromJson(json, Episode::class.java)
    }

    @TypeConverter
    fun toJsonObject(pojoObject: Any?): String {
        return gson.toJson(pojoObject)
    }

    @TypeConverter
    fun toCharacterList(json: String) : List<String> = gson.fromJson(json, Array<String>::class.java).toList()

    @TypeConverter
    fun fromCharactersList(characters: List<String>) : String = gson.toJson(characters)
}