package com.thechefz.e_commerce_demo.presentation_layer.fragments.home.categories

import android.util.Log
import androidx.lifecycle.ViewModel
import com.thechefz.e_commerce_demo.data_layer.entities.CategoryEntity
import com.thechefz.e_commerce_demo.data_layer.entities.OrderEntity
import com.thechefz.e_commerce_demo.data_layer.interactor.CategoryInteractor
import com.thechefz.e_commerce_demo.data_layer.interactor.OrderInteractor
import com.thechefz.e_commerce_demo.data_layer.interactor.PromotionInteractor
import com.thechefz.e_commerce_demo.utils.CombinedLiveEvents

class CategoriesViewModel(
    private val orderInteractor: OrderInteractor,
    private val categoryInteractor: CategoryInteractor,
    private val promotionInteractor: PromotionInteractor
) : ViewModel() {

    val ordersData = CombinedLiveEvents<ArrayList<OrderEntity>>()
    val categoryData = CombinedLiveEvents<ArrayList<CategoryEntity>>()
    val handlePromotion = CombinedLiveEvents<ArrayList<CategoryEntity>>()

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

    fun handleDeepLink(args: CategoriesFragmentArgs) {

        if (args.categoryId.isNullOrEmpty() && args.expiryDate.isNullOrEmpty() && args.minQuantity.isNullOrEmpty()&& args.maxQuantity.isNullOrEmpty() && args.maxPrice.isNullOrEmpty()) {
            //this case mean user don't open app from link
            return
        }
        promotionInteractor.checkPromotion(
            args.categoryId,
            args.expiryDate,
            args.minQuantity,
            args.maxQuantity,
            args.maxPrice
        ) {
            Log.e("ahmed", it)
        }
    }
}