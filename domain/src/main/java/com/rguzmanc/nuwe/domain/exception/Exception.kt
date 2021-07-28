package com.rguzmanc.nuwe.domain.exception

import java.io.IOException

class NetworkException(override val message: String? = null) : IOException(message)
class SessionExpiredException(override val message: String? = null) : IOException(message)
class ResponseException(override val message: String? = null) : Exception(message)

class ServerException(override val message: String? = null) : Exception(message)
class InvalidCredentialsException(override val message: String) : Exception(message)