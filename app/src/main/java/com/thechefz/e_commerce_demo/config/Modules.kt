package com.thechefz.e_commerce_demo.config

import android.content.Context
import com.thechefz.e_commerce_demo.data_layer.interactor.CartInteractor
import com.thechefz.e_commerce_demo.data_layer.interactor.UserInteractor
import com.thechefz.e_commerce_demo.data_layer.remote_ds.CartRemoteDS
import com.thechefz.e_commerce_demo.data_layer.remote_ds.UserRemoteDS
import com.thechefz.e_commerce_demo.data_layer.repositiries.CartRepo
import com.thechefz.e_commerce_demo.data_layer.repositiries.UserRepo
import com.thechefz.e_commerce_demo.presentation_layer.fragments.login.LoginViewModel
import com.thechefz.e_commerce_demo.presentation_layer.fragments.verification_code.VerificationCodeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



val repoModule = module {
    single { UserRepo(get()) }
    single { CartRepo(get()) }
}

val remoteDSModule = module {
    single { UserRemoteDS() }
    single { CartRemoteDS( androidContext()) }
}

val useCaseModule = module {
    single { UserInteractor(get()) }
    single { CartInteractor(get()) }
}


val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { VerificationCodeViewModel(get()) }
}

