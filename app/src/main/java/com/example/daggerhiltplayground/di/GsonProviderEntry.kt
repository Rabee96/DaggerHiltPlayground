package com.example.daggerhiltplayground.di

import com.example.daggerhiltplayground.database.online.APIRequests
import com.google.gson.Gson
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface GsonProviderEntry {
    fun gsonEntryProvider(): Gson
}