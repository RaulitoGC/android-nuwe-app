package com.rguzmanc.nuwe.data.utils

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.rguzmanc.nuwe.domain.exception.ResponseException
import com.rguzmanc.nuwe.domain.exception.ServerException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import retrofit2.Response
import timber.log.Timber

/**
 * This method wrap an API [call] in Flow login and also exposes an extension in order to customize
 * validations over your API response to make it easier to catch errors on the fly.
 * */
suspend fun <T : Any> executeApiCall(
    call: suspend () -> Response<T>,
    validate: Response<T>.() -> Flow<T> = {
        validateNetworkResponse(this)
    }
): Flow<T> {
    val response = call()
    return response.validate()
}

private fun <T : Any> validateNetworkResponse(response: Response<T>): Flow<T> {
    if (response.isSuccessful) {
        val body = response.body()
        if (body != null) {
            return flowOf(body)
        }
        throw ResponseException()
    }

    throw ServerException()
}

/**
 * This method validate if there is internet connection or not
 *
 **/
// TODO:  Update this method
@Suppress("DEPRECATION")
fun isOnline(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = connectivityManager.activeNetworkInfo
    return netInfo != null && netInfo.isConnected
}