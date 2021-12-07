package com.thechefz.e_commerce_demo.data_layer.remote_ds

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thechefz.e_commerce_demo.data_layer.entities.CartEntity
import com.thechefz.e_commerce_demo.utils.Helper
import com.thechefz.e_commerce_demo.utils.OurException


class CartRemoteDS(private val context: Context) {

    fun getCart(
        success: (ArrayList<CartEntity>) -> Unit,
        error: (OurException) -> Unit
    ) {
        val data = Helper.readJsonFromAssets(context, "cart.json")
        val myType = object : TypeToken<ArrayList<CartEntity>?>() {}.type
        val list = Gson().fromJson<ArrayList<CartEntity>?>(data, myType)
        if (list == null || list.isEmpty()) {
            error.invoke(OurException(message = "Try again"))
        } else {
            success.invoke(list)
        }
    }
}