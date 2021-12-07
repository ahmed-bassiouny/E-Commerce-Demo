package com.thechefz.e_commerce_demo.presentation_layer.fragments.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thechefz.e_commerce_demo.data_layer.interactor.UserInteractor

class LoginViewModel(private val userInteractor: UserInteractor) : ViewModel() {

    val loginBtnObserve = MutableLiveData<Boolean>()

    fun isValidInput(emailOrPhone:String,password:String){
        loginBtnObserve.value = userInteractor.isValidInput(emailOrPhone,password)
    }
}