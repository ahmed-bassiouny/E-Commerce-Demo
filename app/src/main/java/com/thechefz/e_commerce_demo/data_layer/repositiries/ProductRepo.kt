package com.thechefz.e_commerce_demo.data_layer.repositiries

import com.thechefz.e_commerce_demo.data_layer.entities.CartEntity
import com.thechefz.e_commerce_demo.data_layer.entities.CategoryEntity
import com.thechefz.e_commerce_demo.data_layer.entities.ProductEntity
import com.thechefz.e_commerce_demo.data_layer.remote_ds.CartRemoteDS
import com.thechefz.e_commerce_demo.data_layer.remote_ds.CategoryRemoteDS
import com.thechefz.e_commerce_demo.data_layer.remote_ds.ProductRemoteDS
import com.thechefz.e_commerce_demo.data_layer.remote_ds.UserRemoteDS
import com.thechefz.e_commerce_demo.utils.OurException

class ProductRepo(private val productRemoteDS: ProductRemoteDS) {

    fun getProducts(success: (ArrayList<ProductEntity>) -> Unit,
                   error: (OurException) -> Unit) = productRemoteDS.getProducts(success, error)
}