package com.example.a963103033239757ba10504dc3857ddc7.data.util

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


fun String.formatDate(): String =
    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this)
        ?.let { SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(it) } ?: ""


fun Int.formatDigit(): String =
    NumberFormat.getNumberInstance(Locale.GERMANY)
        .format(this)
