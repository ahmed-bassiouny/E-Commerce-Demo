package com.thechefz.e_commerce_demo.data_layer.interactor

import com.thechefz.e_commerce_demo.data_layer.entities.CartEntity
import com.thechefz.e_commerce_demo.data_layer.repositiries.CartRepo
import com.thechefz.e_commerce_demo.utils.OurException

class CartInteractor(private val cartRepo: CartRepo) {


    fun getCart(
        success: (ArrayList<CartEntity>) -> Unit,
        error: (OurException) -> Unit
    ) {

        cartRepo.getCart(success, error)
    }

}