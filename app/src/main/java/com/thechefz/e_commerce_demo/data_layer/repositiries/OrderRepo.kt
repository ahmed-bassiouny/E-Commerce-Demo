package com.thechefz.e_commerce_demo.data_layer.repositiries

import com.thechefz.e_commerce_demo.data_layer.entities.CartEntity
import com.thechefz.e_commerce_demo.data_layer.entities.OrderEntity
import com.thechefz.e_commerce_demo.data_layer.remote_ds.CartRemoteDS
import com.thechefz.e_commerce_demo.data_layer.remote_ds.OrderRemoteDS
import com.thechefz.e_commerce_demo.data_layer.remote_ds.UserRemoteDS
import com.thechefz.e_commerce_demo.utils.OurException

class OrderRepo(private val orderRemoteDS: OrderRemoteDS) {

    fun getOrders(success: (ArrayList<OrderEntity>) -> Unit,
                   error: (OurException) -> Unit) = orderRemoteDS.getOrders(success, error)
}