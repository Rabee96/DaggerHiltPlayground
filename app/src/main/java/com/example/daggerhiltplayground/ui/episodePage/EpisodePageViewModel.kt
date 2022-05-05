package com.example.daggerhiltplayground.ui.episodePage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daggerhiltplayground.network.NetworkResult
import com.example.daggerhiltplayground.pojo.character.Character
import com.example.daggerhiltplayground.pojo.episode.Episode
import com.example.daggerhiltplayground.repository.EpisodeRepo
import com.example.daggerhiltplayground.util.ResponseStatus.SUCCESS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodePageViewModel @Inject constructor(
    private val episodeRepo: EpisodeRepo) : ViewModel() {

    private var _episodeInfo = MutableLiveData<NetworkResult<Episode>>()
    val episodeInfo: LiveData<NetworkResult<Episode>>
        get() = _episodeInfo
    private val charactersIDs: MutableList<Int> = emptyList<Int>().toMutableList()

    private var _episodeCast = MutableLiveData<NetworkResult<List<Character>>>()
    val episodeCast: LiveData<NetworkResult<List<Character>>>
        get() = _episodeCast

    fun getEpisode(episodeNumber: String) {
        _episodeInfo.value = NetworkResult.Loading()
        _episodeCast.value = NetworkResult.Loading()
        CoroutineScope(IO).launch {
            val result = episodeRepo.singleEpisodeCAll(episodeNumber)
            if (result.status == SUCCESS) {
                _episodeInfo.postValue(result)
                for (x in result.data!!.characters) {
                    if (x.takeLast(2).contains('/'))
                        charactersIDs.add(x.takeLast(1).toInt())
                    else
                        charactersIDs.add(x.takeLast(2).toInt())
                }
                getEpisodeCast(charactersIDs)

                try {
                   //saveEpisodeIntoDatabase(result.data!!) ?: throw (Exception("Episode $episodeNumber did not added"))
                }catch(e: Exception){
                    Log.e("Rabee","Error is ${e.message}")
                }
            }
        }
    }

    private fun getEpisodeCast(charactersIDs: List<Int>) {
        CoroutineScope(IO).launch {
            _episodeCast.postValue(episodeRepo.gatMultipleCharacters(charactersIDs))
        }
    }
}