package com.thechefz.e_commerce_demo.data_layer.remote_ds

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thechefz.e_commerce_demo.data_layer.entities.ProductEntity
import com.thechefz.e_commerce_demo.utils.Helper
import com.thechefz.e_commerce_demo.utils.OurException


class ProductRemoteDS(private val context: Context) {

    fun getProducts(
        success: (ArrayList<ProductEntity>) -> Unit,
        error: (OurException) -> Unit
    ) {
        val data = Helper.readJsonFromAssets(context, "product.json")
        val myType = object : TypeToken<ArrayList<ProductEntity>?>() {}.type
        val list = Gson().fromJson<ArrayList<ProductEntity>?>(data, myType)
        if (list == null || list.isEmpty()) {
            error.invoke(OurException(message = "Try again"))
        } else {
            success.invoke(list)
        }
    }
}