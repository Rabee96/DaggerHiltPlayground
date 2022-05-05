package com.example.daggerhiltplayground.pojo.charactersList

import com.example.daggerhiltplayground.pojo.character.Character
import com.example.daggerhiltplayground.pojo.global.Info

data class Characters(
    val info: Info,
    val results: MutableList<Character>
)