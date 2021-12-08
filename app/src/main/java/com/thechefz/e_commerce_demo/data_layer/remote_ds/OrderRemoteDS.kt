package com.thechefz.e_commerce_demo.data_layer.remote_ds

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thechefz.e_commerce_demo.data_layer.entities.CartEntity
import com.thechefz.e_commerce_demo.data_layer.entities.OrderEntity
import com.thechefz.e_commerce_demo.utils.Helper
import com.thechefz.e_commerce_demo.utils.OurException


class OrderRemoteDS(private val context: Context) {

    fun getOrders(
        success: (ArrayList<OrderEntity>) -> Unit,
        error: (OurException) -> Unit
    ) {
        val data = Helper.readJsonFromAssets(context, "orders.json")
        val myType = object : TypeToken<ArrayList<OrderEntity>?>() {}.type
        val list = Gson().fromJson<ArrayList<OrderEntity>?>(data, myType)
        if (list == null || list.isEmpty()) {
            error.invoke(OurException(message = "Try again"))
        } else {
            success.invoke(list)
        }
    }
}