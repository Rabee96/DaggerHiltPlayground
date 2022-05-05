package com.example.daggerhiltplayground.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daggerhiltplayground.network.NetworkResult
import com.example.daggerhiltplayground.pojo.character.Character
import com.example.daggerhiltplayground.repository.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepo: HomeRepo) : ViewModel() {

    private val _character: MutableLiveData<NetworkResult<List<Character>>> = MutableLiveData()
    val character: LiveData<NetworkResult<List<Character>>>
        get() = _character

    private val currentCharacterList: MutableList<Character> =
        emptyList<Character>().toMutableList()

    private var pageNumber = 1
    var characterListScrollPosition = 0

    fun characters(page: Int) {
        _character.value = NetworkResult.Loading()
        CoroutineScope(IO).launch {
            val result = async {
                NetworkResult.Success(homeRepo.charactersCAll(page).data!!.results.toList())
            }
            _character.postValue(result.await())
        }
    }

    fun nextPage() {
        pageNumber++
        getMoreCharacters()
    }

    private fun getMoreCharacters() {
        currentCharacterList.addAll(character.value!!.data!!.toMutableList())
        _character.value = NetworkResult.Loading()
        CoroutineScope(IO).launch {
            val result0 = async {
                homeRepo.charactersCAll(pageNumber)
            }
            appendNewCharacters(result0.await().data!!.results)
        }
    }

    private fun appendNewCharacters(newCharacters: List<Character>) {
        currentCharacterList.addAll(newCharacters)
        _character.postValue(
            NetworkResult.Success(
                currentCharacterList
            )
        )
    }

    fun onChaneCharacterListScrollPosition(p: Int) {
        characterListScrollPosition = p
    }

    fun internetConnectionFailure() {
        _character.value = NetworkResult.Failure("Your WIFI is down\nYeaaaaaaah pickle rick")
    }
}