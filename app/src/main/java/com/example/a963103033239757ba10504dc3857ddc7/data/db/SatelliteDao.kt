package com.example.a963103033239757ba10504dc3857ddc7.data.db

import androidx.room.*
import com.example.a963103033239757ba10504dc3857ddc7.data.model.DataModel
import com.example.a963103033239757ba10504dc3857ddc7.data.util.Constants

@Dao
interface SatelliteDao {

    @Query("SELECT * FROM ${Constants.TABLE_NAME} WHERE id = :id LIMIT 1")
    suspend fun getSatelliteById(id: String): DataModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSatellite(dataModel: DataModel)

}