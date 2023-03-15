package com.example.a963103033239757ba10504dc3857ddc7.data.util

import com.example.a963103033239757ba10504dc3857ddc7.data.model.DataModel
import com.example.a963103033239757ba10504dc3857ddc7.data.model.position.PositionModel
import com.example.a963103033239757ba10504dc3857ddc7.domain.model.SatelliteDetailModel

fun DataModel.toSatelliteDetailModel(): SatelliteDetailModel {
    val name = satelliteName
    val date = satelliteDetails.first_flight.formatDate()
    val heightMass = "${satelliteDetails.height}/${satelliteDetails.mass}"
    val coast = satelliteDetails.cost_per_launch.formatDigit()
    val lastPositions = list[0].toFormattedString()
    val positionsList = list
    return SatelliteDetailModel(
        name, date, heightMass, coast, lastPositions, positionsList
    )
}

fun PositionModel.toFormattedString(): String {
    return "($posX,$posY)"
}