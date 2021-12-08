package com.thechefz.e_commerce_demo.presentation_layer.fragments.home.categories

import androidx.lifecycle.ViewModel
import com.thechefz.e_commerce_demo.data_layer.entities.CategoryEntity
import com.thechefz.e_commerce_demo.data_layer.entities.OrderEntity
import com.thechefz.e_commerce_demo.data_layer.interactor.CategoryInteractor
import com.thechefz.e_commerce_demo.data_layer.interactor.OrderInteractor
import com.thechefz.e_commerce_demo.utils.CombinedLiveEvents

class CategoriesViewModel(private val orderInteractor: OrderInteractor,private val categoryInteractor: CategoryInteractor) : ViewModel() {

    val ordersData = CombinedLiveEvents<ArrayList<OrderEntity>>()
    val categoryData = CombinedLiveEvents<ArrayList<CategoryEntity>>()

    init {
        getPastOrder()
        getCategories()
    }

    private fun getCategories() {
        categoryData.setLoading(true)
        categoryInteractor.getCategories({
            categoryData.value = it
        }, {
            categoryData.setError(it)
        })
    }

    private fun getPastOrder() {
        ordersData.setLoading(true)
        orderInteractor.getOrders({
            ordersData.value = it
        }, {
            ordersData.setError(it)
        })
    }
}