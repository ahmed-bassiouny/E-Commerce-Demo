package com.thechefz.e_commerce_demo.data_layer.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryEntity(
    val id:String,
    val name:String,
    val img:String
): Parcelable
