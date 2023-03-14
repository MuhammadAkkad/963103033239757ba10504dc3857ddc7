package com.example.a963103033239757ba10504dc3857ddc7.data.data_source

import android.content.Context
import com.example.a963103033239757ba10504dc3857ddc7.data.model.position.PositionsItemModel
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject

class DataSourceProvider @Inject constructor(@ApplicationContext val context: Context) {

    fun getJsonDataFromAsset(fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

}