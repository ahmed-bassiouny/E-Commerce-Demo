package com.thechefz.e_commerce_demo.data_layer.remote_ds

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thechefz.e_commerce_demo.data_layer.entities.CartEntity
import com.thechefz.e_commerce_demo.data_layer.entities.CategoryEntity
import com.thechefz.e_commerce_demo.utils.Helper
import com.thechefz.e_commerce_demo.utils.OurException


class CategoryRemoteDS(private val context: Context) {

    fun getCategories(
        success: (ArrayList<CategoryEntity>) -> Unit,
        error: (OurException) -> Unit
    ) {
        val data = Helper.readJsonFromAssets(context, "category.json")
        val myType = object : TypeToken<ArrayList<CategoryEntity>?>() {}.type
        val list = Gson().fromJson<ArrayList<CategoryEntity>?>(data, myType)
        if (list == null || list.isEmpty()) {
            error.invoke(OurException(message = "Try again"))
        } else {
            success.invoke(list)
        }
    }
}