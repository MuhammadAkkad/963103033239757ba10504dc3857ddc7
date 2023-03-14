package com.example.a963103033239757ba10504dc3857ddc7.data.di

import com.example.a963103033239757ba10504dc3857ddc7.data.repository.SatelliteListRepositoryImpl
import com.example.a963103033239757ba10504dc3857ddc7.domain.repository.SatelliteListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSatelliteListRepository(
        repository: SatelliteListRepositoryImpl
    ): SatelliteListRepository

}