package com.example.a963103033239757ba10504dc3857ddc7.data.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object JsonHelper {
    fun <T> fromJsonList(jsonArray: String?, clazz: Class<T>?, gson: Gson): List<T> {
        val typeOfT = TypeToken.getParameterized(MutableList::class.java, clazz).type
        return gson.fromJson(jsonArray, typeOfT)
    }

    fun <T> fromJsonObject(jsonArray: String?, clazz: Class<T>, gson: Gson): T {
        return gson.fromJson(jsonArray, clazz)
    }
}