package com.thechefz.e_commerce_demo.data_layer.entities

import com.google.gson.annotations.SerializedName

data class ProductEntity(
    val name: String,
    val img: String,
    @SerializedName("category_id") val categoryId: String
)
