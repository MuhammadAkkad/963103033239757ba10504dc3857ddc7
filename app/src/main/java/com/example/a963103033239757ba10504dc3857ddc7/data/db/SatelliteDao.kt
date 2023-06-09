package com.example.a963103033239757ba10504dc3857ddc7.data.db

import androidx.room.*
import com.example.a963103033239757ba10504dc3857ddc7.data.model.satelliteDetail.SatelliteDetailModelDto
import com.example.a963103033239757ba10504dc3857ddc7.data.util.Constants

@Dao
interface SatelliteDao {

    @Query("SELECT * FROM ${Constants.TABLE_NAME} WHERE id = :id LIMIT 1")
    suspend fun getSatelliteById(id: String): SatelliteDetailModelDto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSatellite(satelliteDetailModelDto: SatelliteDetailModelDto)

}