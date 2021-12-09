package com.thechefz.e_commerce_demo.data_layer.interactor

import com.thechefz.e_commerce_demo.data_layer.entities.ProductEntity
import com.thechefz.e_commerce_demo.data_layer.repositiries.ProductRepo
import com.thechefz.e_commerce_demo.utils.OurException

class ProductInteractor(private val productRepo: ProductRepo) {


    fun getProducts(
        categoryId: String? = null, q: String? = null,
        success: (ArrayList<ProductEntity>) -> Unit,
        error: (OurException) -> Unit
    ) {

        productRepo.getProducts({
            var list = it
            categoryId?.let {
                list = list.filter { item -> item.categoryId == it } as ArrayList<ProductEntity>
            }
            q?.let {
                list = list.filter { item -> item.name.lowercase().contains(it.lowercase()) } as ArrayList<ProductEntity>
            }
            success(list)
        }, error)
    }

}