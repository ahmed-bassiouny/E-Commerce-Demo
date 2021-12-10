package com.thechefz.e_commerce_demo.data_layer.entities

data class PromotionEntity(
    val minQty:Int,
    val maxQty:Int,
    val discountAmount:Double,
    val categoryId:String,
)
