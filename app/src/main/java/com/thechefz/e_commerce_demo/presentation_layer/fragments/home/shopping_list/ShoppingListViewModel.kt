package com.thechefz.e_commerce_demo.presentation_layer.fragments.home.shopping_list

import androidx.lifecycle.ViewModel
import com.thechefz.e_commerce_demo.data_layer.entities.CartEntity
import com.thechefz.e_commerce_demo.data_layer.interactor.CartInteractor
import com.thechefz.e_commerce_demo.utils.CombinedLiveEvents

class ShoppingListViewModel(private val cartInteractor: CartInteractor) : ViewModel() {

    val cartData = CombinedLiveEvents<ArrayList<CartEntity>>()

    init {
        fetchCart()
    }
    private fun fetchCart() {
        cartData.setLoading(true)
        cartInteractor.getCart({
            cartData.value = it
        }, {
            cartData.setError(it)
        })
    }
}