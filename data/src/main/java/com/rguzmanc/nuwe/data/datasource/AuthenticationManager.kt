package com.rguzmanc.nuwe.data.datasource

import com.rguzmanc.nuwe.data.datasource.remote.RemoteDataSource
import com.rguzmanc.nuwe.domain.entity.None
import com.rguzmanc.nuwe.domain.system.AuthenticationSystem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class AuthenticationManager(private val remoteDataSource: RemoteDataSource): AuthenticationSystem {

    @ExperimentalCoroutinesApi
    override suspend fun login(username: String, password: String) = flow<None> {
        remoteDataSource.login(username, password).collect()
    }

    override suspend fun loginWithSocialNetwork() {

    }
}