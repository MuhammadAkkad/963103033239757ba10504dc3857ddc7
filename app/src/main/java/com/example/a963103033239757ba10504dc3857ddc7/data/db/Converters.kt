package com.example.a963103033239757ba10504dc3857ddc7.data.db

import androidx.room.TypeConverter
import com.example.a963103033239757ba10504dc3857ddc7.data.model.position.PositionModel
import com.example.a963103033239757ba10504dc3857ddc7.data.model.satelliteDetail.SatelliteDetailModelItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun satelliteDetailModelItemToString(satelliteDetailModelItem: SatelliteDetailModelItem): String {
        return Gson().toJson(satelliteDetailModelItem)
    }

    @TypeConverter
    fun stringToSatelliteDetailModelItem(satelliteDetailModelItem: String): SatelliteDetailModelItem {
        return Gson().fromJson(satelliteDetailModelItem, SatelliteDetailModelItem::class.java)
    }

    @TypeConverter
    fun listPositionModelToString(satelliteDetailModelItem: List<PositionModel>): String {
        return Gson().toJson(satelliteDetailModelItem).toString()
    }

    @TypeConverter
    fun stringToListPositionModel(satelliteDetailModelItem: String): List<PositionModel> {
        val itemType = object : TypeToken<List<PositionModel>>() {}.type
        return Gson().fromJson(satelliteDetailModelItem, itemType)
    }

}