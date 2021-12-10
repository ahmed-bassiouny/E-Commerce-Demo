package com.thechefz.e_commerce_demo.utils.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String?.fromTimestampToDate(): Date {
    return try {
        Date((this?.toLongOrNull() ?: 0) * 1000)
    } catch (e: Exception) {
        e.printStackTrace()
        Date()
    }
}

fun String?.toDateTime(s: String): String? {
    return try {
        val sdf = SimpleDateFormat(s, Locale.getDefault())
        sdf.format(fromTimestampToDate())
    } catch (e: Exception) {
        e.toString()
        ""
    }
}

fun String?.intValue(): Int {
    return this?.toIntOrNull() ?: 0
}

fun String?.value(): String {
    return this ?: ""
}