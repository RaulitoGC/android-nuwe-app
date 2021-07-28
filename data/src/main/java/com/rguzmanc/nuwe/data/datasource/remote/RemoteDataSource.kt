package com.rguzmanc.nuwe.data.datasource.remote

import com.google.firebase.auth.FirebaseAuth
import com.rguzmanc.nuwe.domain.entity.None
import com.rguzmanc.nuwe.domain.exception.ServerException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber

class RemoteDataSource(private val firebaseAuth: FirebaseAuth) {

    @ExperimentalCoroutinesApi
    suspend fun login(username: String, password: String): Flow<None> = callbackFlow {

        val authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            firebaseAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        trySend(None())
                    } else {
                        val exception = task.exception
                        Timber.e(exception)
                        close(exception)
                    }
                }
        }
        firebaseAuth.addAuthStateListener(authListener)
        awaitClose {
            firebaseAuth.removeAuthStateListener(authListener)
        }
    }
}