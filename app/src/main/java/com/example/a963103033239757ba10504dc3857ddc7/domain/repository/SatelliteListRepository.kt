package com.example.a963103033239757ba10504dc3857ddc7.domain.repository

import com.example.a963103033239757ba10504dc3857ddc7.data.model.satellite.SatelliteListModelItem
import com.example.a963103033239757ba10504dc3857ddc7.domain.model.SatelliteDetailModel

interface SatelliteListRepository {
    fun getSatelliteList(): List<SatelliteListModelItem>
    fun getSatelliteDetails(id : String): SatelliteDetailModel?
}