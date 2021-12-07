package com.thechefz.e_commerce_demo.presentation_layer.fragments.verification_code

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thechefz.e_commerce_demo.data_layer.interactor.UserInteractor
import com.thechefz.e_commerce_demo.utils.CombinedLiveEvents

class VerificationCodeViewModel(private val userInteractor: UserInteractor) : ViewModel() {

    val confirmBtnObserve = MutableLiveData<Boolean>()
    val validCodeObserve = CombinedLiveEvents<Boolean>()

    fun isValidCode(code: String) {
        confirmBtnObserve.value = userInteractor.isValidCode(code)
    }

    fun checkCode(code: String) {
        validCodeObserve.setLoading(true)
        userInteractor.checkCode(code, {
            validCodeObserve.value = true
        }, {
            validCodeObserve.setError(it)
        })
    }

}