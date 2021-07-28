package com.rguzmanc.nuwe.domain.usecase

import com.rguzmanc.nuwe.domain.system.AuthenticationSystem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginUseCase(private val authenticationSystem: AuthenticationSystem) {

    suspend operator fun invoke(username: String, password: String) = flow<Unit> {
        return@flow authenticationSystem.login(username = username, password = password).collect()
    }.flowOn(Dispatchers.IO)
}