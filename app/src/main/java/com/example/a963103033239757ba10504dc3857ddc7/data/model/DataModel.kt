package com.example.a963103033239757ba10504dc3857ddc7.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.a963103033239757ba10504dc3857ddc7.data.model.position.PositionModel
import com.example.a963103033239757ba10504dc3857ddc7.data.model.satelliteDetail.SatelliteDetailModelItem
import com.example.a963103033239757ba10504dc3857ddc7.data.util.Constants

@Entity(tableName = Constants.TABLE_NAME)
data class DataModel(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    val satelliteName: String,
    val satelliteDetails: SatelliteDetailModelItem,
    val list: List<PositionModel>
)
