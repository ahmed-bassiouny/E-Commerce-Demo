package com.thechefz.e_commerce_demo.presentation_layer.fragments.product_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thechefz.e_commerce_demo.data_layer.entities.ProductEntity
import com.thechefz.e_commerce_demo.data_layer.interactor.PromotionInteractor

class ProductDetailsViewModel(private val promotionInteractor: PromotionInteractor) : ViewModel() {

    private var count = 1
    val countLiveData = MutableLiveData(count)
    val productLiveData = MutableLiveData<ProductEntity>()
    val promotionErrorLiveData = MutableLiveData<String?>()

    fun addCount() {
        count += 1
        countLiveData.value = count
        handlePromotion()
    }

    fun removeCount() {
        if (count == 1) return
        count -= 1
        countLiveData.value = count
        handlePromotion()
    }

    private fun handlePromotion() {
        productLiveData.value?.let {
            promotionInteractor.handlePromotion(it,count, {
                productLiveData.value = it
            }, {
                promotionErrorLiveData.value = it
            })
        }

    }

    fun init(product: ProductEntity) {
        productLiveData.value = product
        handlePromotion()
    }
}