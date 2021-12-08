package com.thechefz.e_commerce_demo.presentation_layer.fragments.home.categories

import androidx.lifecycle.ViewModel
import com.thechefz.e_commerce_demo.data_layer.entities.OrderEntity
import com.thechefz.e_commerce_demo.data_layer.interactor.OrderInteractor
import com.thechefz.e_commerce_demo.utils.CombinedLiveEvents

class CategoriesViewModel(private val orderInteractor: OrderInteractor) : ViewModel() {

    val ordersData = CombinedLiveEvents<ArrayList<OrderEntity>>()

    init {
        getUserInfo()
        getPastOrder()
        getCategories()
    }

    private fun getCategories() {

    }

    private fun getPastOrder() {
        ordersData.setLoading(true)
        orderInteractor.getOrders({
            ordersData.value = it
        }, {
            ordersData.setError(it)
        })

    }

    private fun getUserInfo() {

    }
}