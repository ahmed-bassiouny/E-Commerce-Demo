package com.thechefz.e_commerce_demo.data_layer.repositiries

import com.thechefz.e_commerce_demo.data_layer.entities.CartEntity
import com.thechefz.e_commerce_demo.data_layer.remote_ds.CartRemoteDS
import com.thechefz.e_commerce_demo.data_layer.remote_ds.UserRemoteDS
import com.thechefz.e_commerce_demo.utils.OurException

class CartRepo(private val cartRemoteDS: CartRemoteDS) {

    fun getCart(success: (ArrayList<CartEntity>) -> Unit,
                   error: (OurException) -> Unit) = cartRemoteDS.getCart(success, error)
}