package com.example.a963103033239757ba10504dc3857ddc7.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.a963103033239757ba10504dc3857ddc7.data.model.satelliteDetail.SatelliteDetailModelDto

@Database(entities = [SatelliteDetailModelDto::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun satelliteDao(): SatelliteDao
}