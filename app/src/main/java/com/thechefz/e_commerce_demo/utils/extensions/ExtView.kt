package com.thechefz.e_commerce_demo.utils.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.showImage(url:String?){
    Glide.with(context)
        .load(url)
        .into(this)
}
