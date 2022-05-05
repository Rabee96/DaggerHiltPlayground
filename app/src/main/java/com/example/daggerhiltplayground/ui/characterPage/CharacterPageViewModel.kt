package com.example.daggerhiltplayground.ui.characterPage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daggerhiltplayground.pojo.character.Character

class CharacterPageViewModel : ViewModel() {

    val characterInfo : MutableLiveData<Character> = MutableLiveData()
}