package com.thechefz.e_commerce_demo.data_layer.interactor

import com.thechefz.e_commerce_demo.data_layer.entities.OrderEntity
import com.thechefz.e_commerce_demo.data_layer.repositiries.OrderRepo
import com.thechefz.e_commerce_demo.utils.OurException

class OrderInteractor(private val orderRepo: OrderRepo) {


    fun getOrders(
        success: (ArrayList<OrderEntity>) -> Unit,
        error: (OurException) -> Unit
    ) {

        orderRepo.getOrders(success, error)
    }

}