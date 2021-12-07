package com.thechefz.e_commerce_demo.data_layer.interactor

import com.thechefz.e_commerce_demo.data_layer.repositiries.UserRepo
import com.thechefz.e_commerce_demo.utils.OurException
import com.thechefz.e_commerce_demo.utils.extensions.Validator
import com.thechefz.e_commerce_demo.utils.extensions.Validator.CODE_LENGTH

class UserInteractor(private val userRepo: UserRepo) {

    fun isValidInput(emailOrPhone: String, password: String): Boolean {
        // we valid input based on email or phone and password
        return (Validator.isValidEmail(emailOrPhone) || Validator.isValidPhone(emailOrPhone)) && Validator.isValidPassword(
            password
        )
    }

    fun isValidCode(code: String): Boolean {

        return code.length == CODE_LENGTH
    }

    fun checkCode(code: String, success: () -> Unit, error: (OurException) -> Unit) {
        userRepo.verifyCode(code, { success.invoke() }, {
            error.invoke(it)
        })
    }
}