package com.thechefz.e_commerce_demo.data_layer.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductEntity(
    val name: String,
    val img: String,
    val rating: String,
    val description: String,
    val price: String,
    var promotionPrice: String? = null,
    var totalPriceAfterPromotion: String? = null,
    var totalPrice: String? = null,
    @SerializedName("category_id") val categoryId: String
): Parcelable
