package com.thechefz.e_commerce_demo.utils.extensions

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.addFragment(
    fragment: Fragment,
    @IdRes id: Int,
    addToBackStack: Boolean = false,
    tag: String? = null
) {

    val trans = supportFragmentManager.beginTransaction()

    if (tag.isNullOrEmpty().not())
        trans.add(id, fragment,tag)
    else
        trans.add(id, fragment)

    if (addToBackStack)
        trans.addToBackStack("")


    trans.commit()
}