package com.example.a963103033239757ba10504dc3857ddc7.domain.model

import com.example.a963103033239757ba10504dc3857ddc7.data.model.position.PositionModel

data class SatelliteDetailModel(
    val name: String,
    val date: String,
    val height_mass: String,
    var coast: String,
    var lastPosition: String,
    val positionsList: List<PositionModel>
) : java.io.Serializable
