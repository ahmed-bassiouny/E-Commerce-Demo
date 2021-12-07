package com.thechefz.e_commerce_demo.utils

import android.content.Context

object Helper {
    fun readJsonFromAssets(context: Context, filePath: String): String {
        return context.assets.open(filePath).bufferedReader().use { reader ->
            reader.readText()
        }
    }
}