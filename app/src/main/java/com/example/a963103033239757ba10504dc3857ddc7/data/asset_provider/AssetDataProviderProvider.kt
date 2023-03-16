package com.example.a963103033239757ba10504dc3857ddc7.data.asset_provider

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AssetDataProviderProvider @Inject constructor(
    @ApplicationContext val context: Context
) : AutoCloseable {

    private val assetManager: AssetManager = context.assets

    fun getJsonFromAsset(fileName: String): String {
        try {
            return assetManager.open(fileName).bufferedReader().use { it.readText() }
        } catch (e: java.lang.Exception) {
            Log.e("assetManager", e.message.toString())
        }
        return String()
    }

    override fun close() {
        assetManager.close()
    }

}