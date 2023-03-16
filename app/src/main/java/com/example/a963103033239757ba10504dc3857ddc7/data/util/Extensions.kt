package com.example.a963103033239757ba10504dc3857ddc7.data.util

import com.example.a963103033239757ba10504dc3857ddc7.data.model.position.PositionModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


fun String.formatDate(): String =
    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this)
        ?.let { SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(it) } ?: this


fun Int.formatDigit(): String =
    NumberFormat.getNumberInstance(Locale.GERMANY)
        .format(this)

fun PositionModel.toPositionFormat(): String {
    return "($posX,$posY)"
}