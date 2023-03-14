package com.example.a963103033239757ba10504dc3857ddc7.data.model

import com.example.a963103033239757ba10504dc3857ddc7.data.model.position.PositionModel
import com.example.a963103033239757ba10504dc3857ddc7.data.model.satellite.SatelliteListModelItem
import com.example.a963103033239757ba10504dc3857ddc7.data.model.satelliteDetail.SatelliteDetailModelItem

data class DataModel(
    val satelliteName: String,
    val satelliteDetails: SatelliteDetailModelItem,
    val list: List<PositionModel>
)
