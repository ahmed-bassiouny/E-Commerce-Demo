package com.thechefz.e_commerce_demo.data_layer.repositiries

import com.thechefz.e_commerce_demo.data_layer.remote_ds.UserRemoteDS
import com.thechefz.e_commerce_demo.utils.OurException

class UserRepo(private val userRemoteDS: UserRemoteDS) {

    fun verifyCode(code: String, success: () -> Unit, error: (OurException) -> Unit) = userRemoteDS.verifyCode(code,success,error)
}