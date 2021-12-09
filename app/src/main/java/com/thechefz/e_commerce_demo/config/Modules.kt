package com.thechefz.e_commerce_demo.config

import android.content.Context
import com.thechefz.e_commerce_demo.data_layer.interactor.*
import com.thechefz.e_commerce_demo.data_layer.remote_ds.*
import com.thechefz.e_commerce_demo.data_layer.repositiries.*
import com.thechefz.e_commerce_demo.presentation_layer.fragments.home.categories.CategoriesViewModel
import com.thechefz.e_commerce_demo.presentation_layer.fragments.home.product_list.ProductListViewModel
import com.thechefz.e_commerce_demo.presentation_layer.fragments.home.shopping_list.ShoppingListViewModel
import com.thechefz.e_commerce_demo.presentation_layer.fragments.login.LoginViewModel
import com.thechefz.e_commerce_demo.presentation_layer.fragments.verification_code.VerificationCodeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



val repoModule = module {
    single { UserRepo(get()) }
    single { CartRepo(get()) }
    single { OrderRepo(get()) }
    single { CategoryRepo(get()) }
    single { ProductRepo(get()) }
}

val remoteDSModule = module {
    single { UserRemoteDS() }
    single { CartRemoteDS( androidContext()) }
    single { OrderRemoteDS( androidContext()) }
    single { CategoryRemoteDS( androidContext()) }
    single { ProductRemoteDS( androidContext()) }
}

val useCaseModule = module {
    single { UserInteractor(get()) }
    single { CartInteractor(get()) }
    single { OrderInteractor(get()) }
    single { CategoryInteractor(get()) }
    single { ProductInteractor(get()) }
}


val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { VerificationCodeViewModel(get()) }
    viewModel { ShoppingListViewModel(get()) }
    viewModel { CategoriesViewModel(get(),get()) }
    viewModel { ProductListViewModel(get()) }
}

