package com.thechefz.e_commerce_demo.data_layer.interactor

import com.thechefz.e_commerce_demo.data_layer.entities.CartEntity
import com.thechefz.e_commerce_demo.data_layer.entities.CategoryEntity
import com.thechefz.e_commerce_demo.data_layer.repositiries.CartRepo
import com.thechefz.e_commerce_demo.data_layer.repositiries.CategoryRepo
import com.thechefz.e_commerce_demo.utils.OurException

class CategoryInteractor(private val categoryRepo: CategoryRepo) {


    fun getCategories(
        success: (ArrayList<CategoryEntity>) -> Unit,
        error: (OurException) -> Unit
    ) {

        categoryRepo.getCategories(success, error)
    }

}