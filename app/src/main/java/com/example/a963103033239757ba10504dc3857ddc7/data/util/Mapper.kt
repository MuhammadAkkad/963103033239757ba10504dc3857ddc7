package com.example.a963103033239757ba10504dc3857ddc7.data.util

import com.example.a963103033239757ba10504dc3857ddc7.data.model.satelliteDetail.SatelliteDetailModelDto
import com.example.a963103033239757ba10504dc3857ddc7.domain.model.SatelliteDetailModel

fun SatelliteDetailModelDto.toSatelliteDetailModel(): SatelliteDetailModel {

    val name = satelliteName
    val date = satelliteDetails.first_flight.formatDate()
    val heightMass = "${satelliteDetails.height}/${satelliteDetails.mass}"
    val coast = satelliteDetails.cost_per_launch.formatDigit()
    val lastPositions = String()
    val positionsList = list

    return SatelliteDetailModel(
        name, date, heightMass, coast, lastPositions, positionsList
    )
}

