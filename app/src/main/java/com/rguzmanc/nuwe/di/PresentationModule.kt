package com.rguzmanc.nuwe.di

import com.rguzmanc.nuwe.data.di.DataServiceLocator
import com.rguzmanc.nuwe.domain.usecase.LoginUseCase
import com.rguzmanc.nuwe.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        LoginViewModel(
            loginUseCase = LoginUseCase(
                authenticationSystem = DataServiceLocator.provideAuthenticationSystem(context = get())
            )
        )
    }
}