package com.example.a963103033239757ba10504dc3857ddc7.data.repository

import android.util.Log
import com.example.a963103033239757ba10504dc3857ddc7.data.asset_provider.AssetDataProviderProvider
import com.example.a963103033239757ba10504dc3857ddc7.data.db.SatelliteDao
import com.example.a963103033239757ba10504dc3857ddc7.data.model.FileNameEnum.*
import com.example.a963103033239757ba10504dc3857ddc7.data.model.position.PositionModel
import com.example.a963103033239757ba10504dc3857ddc7.data.model.position.PositionsItemObjectModel
import com.example.a963103033239757ba10504dc3857ddc7.data.model.satellite.SatelliteListModelItem
import com.example.a963103033239757ba10504dc3857ddc7.data.model.satelliteDetail.SatelliteDetailModelDto
import com.example.a963103033239757ba10504dc3857ddc7.data.model.satelliteDetail.SatelliteDetailModelItem
import com.example.a963103033239757ba10504dc3857ddc7.data.util.JsonHelper
import com.example.a963103033239757ba10504dc3857ddc7.data.util.ResourceStatus
import com.example.a963103033239757ba10504dc3857ddc7.data.util.toSatelliteDetailModel
import com.example.a963103033239757ba10504dc3857ddc7.domain.model.SatelliteDetailModel
import com.example.a963103033239757ba10504dc3857ddc7.domain.repository.SatelliteListRepository
import com.google.gson.Gson
import com.google.gson.JsonParseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SatelliteListRepositoryImpl @Inject constructor(
    private val assetProvider: AssetDataProviderProvider,
    private val dao: SatelliteDao,
    private val gson: Gson
) : SatelliteListRepository {

    override suspend fun getSatelliteList(): Flow<ResourceStatus<List<SatelliteListModelItem>>> {
        return flow {
            try {
                assetProvider.getJsonFromAsset(SATELLITE.value).let {
                    val list =
                        JsonHelper.fromJsonList(
                            it,
                            SatelliteListModelItem::class.java,
                            gson
                        )
                    emit(ResourceStatus.success(list))
                }
            } catch (e: JsonParseException) {
                emit(ResourceStatus.error(e.stackTrace.toString()))
                Log.e("Error while fetching data", e.stackTrace.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getSatelliteDetails(id: String): Flow<ResourceStatus<SatelliteDetailModel?>> {
        return flow {
            try {
                emit(ResourceStatus.success(getData(id).toSatelliteDetailModel()))
            } catch (e: JsonParseException) {
                emit(ResourceStatus.error(e.stackTrace.toString()))
                Log.e("Error while fetching data", e.stackTrace.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun getData(id: String): SatelliteDetailModelDto {
        // if data presents in db return it else get it from json file
        return fetchFromDb(id) ?: fetchFromJson(id)
    }

    private suspend fun fetchFromDb(id: String): SatelliteDetailModelDto? {
        return dao.getSatelliteById(id)
    }

    private suspend fun fetchFromJson(id: String): SatelliteDetailModelDto {
        val name = getNameById(id)
        val details = getDetailById(id)
        val positions = getPositionsById(id)
        val satelliteDetailModelDto =
            (SatelliteDetailModelDto(
                id.toInt(),
                name,
                details,
                positions
            ))
        saveToDb(satelliteDetailModelDto) // TODO: find better logic.
        return satelliteDetailModelDto
    }

    private suspend fun saveToDb(satelliteDetailModelDto: SatelliteDetailModelDto) {
        dao.insertSatellite(satelliteDetailModelDto)
    }

    private fun getNameById(id: String): String {
        val satelliteList = assetProvider.getJsonFromAsset(SATELLITE.value)
        val satelliteListObject =
            JsonHelper.fromJsonList(satelliteList, SatelliteListModelItem::class.java, gson)
        return satelliteListObject.single { it.id.toString() == id }.name
    // TODO get the exact element from JSON file immediately instead of getting the whole file then filtering out
    }

    private fun getDetailById(id: String): SatelliteDetailModelItem {
        val satellite = assetProvider.getJsonFromAsset(SATELLITE_DETAILS.value)
        val satelliteObject =
            JsonHelper.fromJsonList(satellite, SatelliteDetailModelItem::class.java, gson)
        return satelliteObject.single { it.id.toString() == id }
    }

    private fun getPositionsById(id: String): List<PositionModel> {
        val positions = assetProvider.getJsonFromAsset(POSITIONS.value)
        val positionsObject =
            JsonHelper.fromJsonObject(positions, PositionsItemObjectModel::class.java, gson)
        return positionsObject.list.single { it.id == id }
            .positions
    }

}