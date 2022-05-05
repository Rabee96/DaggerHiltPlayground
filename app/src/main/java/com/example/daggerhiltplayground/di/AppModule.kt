package com.example.daggerhiltplayground.di

import com.example.daggerhiltplayground.BuildConfig.BASE_URL
import com.example.daggerhiltplayground.database.online.APIRequests
import com.example.daggerhiltplayground.util.PojoTypeConverter
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun gsonProviders(): Gson = Gson().newBuilder().create()

    @Singleton
    @Provides
    fun retrofit(gson: Gson): Retrofit.Builder =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson))

    @Singleton
    @Provides
    fun apiRequestInstance(retrofit: Retrofit.Builder): APIRequests =
        retrofit.build().create(APIRequests::class.java)

    @Provides
    fun typeConverterInstance(gson: Gson) : PojoTypeConverter = PojoTypeConverter(gson)

}