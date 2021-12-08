package com.thechefz.e_commerce_demo.data_layer.repositiries

import com.thechefz.e_commerce_demo.data_layer.entities.CartEntity
import com.thechefz.e_commerce_demo.data_layer.entities.CategoryEntity
import com.thechefz.e_commerce_demo.data_layer.remote_ds.CartRemoteDS
import com.thechefz.e_commerce_demo.data_layer.remote_ds.CategoryRemoteDS
import com.thechefz.e_commerce_demo.data_layer.remote_ds.UserRemoteDS
import com.thechefz.e_commerce_demo.utils.OurException

class CategoryRepo(private val categoryRemoteDS: CategoryRemoteDS) {

    fun getCategories(success: (ArrayList<CategoryEntity>) -> Unit,
                   error: (OurException) -> Unit) = categoryRemoteDS.getCategories(success, error)
}