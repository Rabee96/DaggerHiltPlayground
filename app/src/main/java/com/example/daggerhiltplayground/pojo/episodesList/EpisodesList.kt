package com.example.daggerhiltplayground.pojo.episodesList

import com.example.daggerhiltplayground.pojo.global.Info
import com.example.daggerhiltplayground.pojo.episode.Episode

data class EpisodesList(
    val info: Info,
    val results: List<Episode>
)