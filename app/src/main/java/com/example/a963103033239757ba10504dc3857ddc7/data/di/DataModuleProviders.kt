package com.example.a963103033239757ba10504dc3857ddc7.data.di

import android.content.Context
import androidx.room.Room
import com.example.a963103033239757ba10504dc3857ddc7.data.db.AppDatabase
import com.example.a963103033239757ba10504dc3857ddc7.data.db.SatelliteDao
import com.example.a963103033239757ba10504dc3857ddc7.data.util.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModuleProviders {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        /*
        * serializeNulls(): even null values will be serialized. By default, Gson omits the null fields.
        * */
        return GsonBuilder().serializeNulls().create()
    }

    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideSatelliteDao(appDatabase: AppDatabase): SatelliteDao {
        return appDatabase.satelliteDao()
    }
}