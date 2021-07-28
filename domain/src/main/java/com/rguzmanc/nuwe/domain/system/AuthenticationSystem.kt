package com.rguzmanc.nuwe.domain.system

import com.rguzmanc.nuwe.domain.entity.None
import kotlinx.coroutines.flow.Flow

interface AuthenticationSystem {

    suspend fun login(username: String, password: String) : Flow<None>
    suspend fun loginWithSocialNetwork()
}