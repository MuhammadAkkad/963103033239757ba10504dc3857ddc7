package com.example.a963103033239757ba10504dc3857ddc7.data.asset_provider

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AssetDataProviderProvider @Inject constructor(@ApplicationContext val context: Context) {

    fun getJsonFromAsset(fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }

}