package com.example.a963103033239757ba10504dc3857ddc7.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        /*
        * serializeNulls(): even null values will be serialized. By default, Gson omits the null fields.
        * */
        return GsonBuilder().serializeNulls().create()

    }
}