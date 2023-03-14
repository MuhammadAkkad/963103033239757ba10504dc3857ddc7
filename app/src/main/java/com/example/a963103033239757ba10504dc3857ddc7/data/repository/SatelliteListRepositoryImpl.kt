package com.example.a963103033239757ba10504dc3857ddc7.data.repository

import com.example.a963103033239757ba10504dc3857ddc7.data.data_source.DataSourceProvider
import com.example.a963103033239757ba10504dc3857ddc7.data.util.toSatelliteDetailModel
import com.example.a963103033239757ba10504dc3857ddc7.data.model.DataModel
import com.example.a963103033239757ba10504dc3857ddc7.data.model.FileNameEnum.*
import com.example.a963103033239757ba10504dc3857ddc7.data.model.position.PositionModel
import com.example.a963103033239757ba10504dc3857ddc7.data.model.position.PositionsItemObjectModel
import com.example.a963103033239757ba10504dc3857ddc7.data.model.satellite.SatelliteListModelItem
import com.example.a963103033239757ba10504dc3857ddc7.data.model.satelliteDetail.SatelliteDetailModelItem
import com.example.a963103033239757ba10504dc3857ddc7.domain.model.SatelliteDetailModel
import com.example.a963103033239757ba10504dc3857ddc7.domain.repository.SatelliteListRepository
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class SatelliteListRepositoryImpl @Inject constructor(
    private val dataSourceProvider: DataSourceProvider,
    private val gson: Gson
) :
    SatelliteListRepository {

    override fun getSatelliteList(): List<SatelliteListModelItem> {
        val jsonString = dataSourceProvider.getJsonDataFromAsset("satellite-list.json")
        jsonString?.let {
            val itemType = object : TypeToken<List<SatelliteListModelItem>>() {}.type
            return gson.fromJson(jsonString, itemType)
        }
        return arrayListOf()
    }

    override fun getSatelliteDetails(id: String): SatelliteDetailModel? {
        return try {
            val name = getNameById(id)
            val details = getDetailById(id)
            val positions = getPositionsById(id)
            DataModel(name, details, positions).toSatelliteDetailModel()
        } catch (e: JsonParseException) {
            e.stackTrace
            null
        }
    }

    private fun getNameById(id: String): String {
        val satelliteList = dataSourceProvider.getJsonDataFromAsset(SATELLITE.value)
        val itemType = object : TypeToken<List<SatelliteListModelItem>>() {}.type
        val satelliteListObject =
            gson.fromJson<List<SatelliteListModelItem>>(satelliteList, itemType)
        return satelliteListObject.single { it.id.toString() == id }.name
    }

    private fun getDetailById(id: String): SatelliteDetailModelItem {
        val satellite = dataSourceProvider.getJsonDataFromAsset(SATELLITE_DETAILS.value)
        val itemType = object : TypeToken<List<SatelliteDetailModelItem>>() {}.type
        val satelliteObject = gson.fromJson<List<SatelliteDetailModelItem>>(satellite, itemType)
        return satelliteObject.single { it.id.toString() == id }
    }

    private fun getPositionsById(id: String): List<PositionModel> {
        val positions = dataSourceProvider.getJsonDataFromAsset(POSITIONS.value)
        val itemType = object : TypeToken<PositionsItemObjectModel>() {}.type
        val positionsObject = gson.fromJson<PositionsItemObjectModel>(positions, itemType)
        return positionsObject.list.single { it.id == id }
            .positions
    }

}