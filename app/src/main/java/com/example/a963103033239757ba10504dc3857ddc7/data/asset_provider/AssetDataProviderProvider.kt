package com.example.a963103033239757ba10504dc3857ddc7.data.asset_provider

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject

class AssetDataProviderProvider @Inject constructor(@ApplicationContext val context: Context) {

    fun getJsonFromAsset(fileName: String): String? {
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