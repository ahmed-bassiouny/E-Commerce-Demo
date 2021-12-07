package com.thechefz.e_commerce_demo.utils.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard(){
    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view?.windowToken, 0)
}

fun Fragment.replaceFragment(
    fragment: Fragment,
    @IdRes id: Int,
    addToBackStack: Boolean = false,
    showAnimation: Boolean = true
) {

    val trans = activity?.supportFragmentManager?.beginTransaction()

    trans?.replace(id, fragment)

    if (addToBackStack)
        trans?.addToBackStack("")

    trans?.commit()
}

fun Fragment.addFragment(
    fragment: Fragment,
    @IdRes id: Int,
    addToBackStack: Boolean = false,
    cancelAnimation: Boolean = false
) {

    val trans = activity?.supportFragmentManager?.beginTransaction()

    trans?.add(id, fragment)

    if (addToBackStack)
        trans?.addToBackStack("")

    trans?.commit()
}