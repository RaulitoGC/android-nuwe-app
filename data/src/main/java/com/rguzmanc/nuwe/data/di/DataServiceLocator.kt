package com.rguzmanc.nuwe.data.di

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rguzmanc.nuwe.data.datasource.AuthenticationManager
import com.rguzmanc.nuwe.data.datasource.remote.RemoteDataSource
import com.rguzmanc.nuwe.domain.system.AuthenticationSystem

object DataServiceLocator {

    private val lock = Any()

    @Volatile
    private var authenticationSystem: AuthenticationSystem? = null

    fun provideAuthenticationSystem(context: Context): AuthenticationSystem =  authenticationSystem ?: synchronized(lock){
        AuthenticationManager(provideRemoteDataSource(context)).also {
            authenticationSystem = it
        }
    }

    private fun provideRemoteDataSource(context: Context): RemoteDataSource{
        FirebaseApp.initializeApp(context)
        return RemoteDataSource(Firebase.auth)
    }
}