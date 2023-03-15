package com.example.a963103033239757ba10504dc3857ddc7.domain.repository

import com.example.a963103033239757ba10504dc3857ddc7.data.model.satellite.SatelliteListModelItem
import com.example.a963103033239757ba10504dc3857ddc7.data.util.ResourceStatus
import com.example.a963103033239757ba10504dc3857ddc7.domain.model.SatelliteDetailModel
import kotlinx.coroutines.flow.Flow

interface SatelliteListRepository {
    suspend fun getSatelliteList(): Flow<ResourceStatus<List<SatelliteListModelItem>>>
    suspend fun getSatelliteDetails(id: String): Flow<ResourceStatus<SatelliteDetailModel?>>
}