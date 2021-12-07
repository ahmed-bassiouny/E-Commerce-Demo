package com.thechefz.e_commerce_demo.data_layer.remote_ds

import com.thechefz.e_commerce_demo.utils.OurException

class UserRemoteDS {

    fun verifyCode(code: String, success: () -> Unit, error: (OurException) -> Unit) {
        if (code == "5555")
            success.invoke()
        else error.invoke(OurException(message = "Invalid Code"))
    }
}