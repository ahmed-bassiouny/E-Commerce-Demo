package com.thechefz.e_commerce_demo.presentation_layer.fragments.home.product_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thechefz.e_commerce_demo.data_layer.entities.CategoryEntity
import com.thechefz.e_commerce_demo.data_layer.entities.ProductEntity
import com.thechefz.e_commerce_demo.data_layer.interactor.ProductInteractor
import com.thechefz.e_commerce_demo.utils.CombinedLiveEvents

class ProductListViewModel(private val productInteractor: ProductInteractor) : ViewModel() {

    val data = CombinedLiveEvents<ArrayList<ProductEntity>>()
    val title = MutableLiveData<String>()
    var selectedCategory: CategoryEntity? = null
    fun fetchData(q: String? = null) {
        productInteractor.getProducts(selectedCategory?.id, q, {
            data.value = it
        }, {
            data.setError(it)
        })
    }

    fun init(category: CategoryEntity?) {
        selectedCategory = category
        title.value = category?.name ?: "Product List"
        fetchData()
    }
}