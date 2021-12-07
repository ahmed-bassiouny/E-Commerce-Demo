package com.thechefz.e_commerce_demo.utils

import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class OurException(
    private val throwable: Throwable? = null,
    private val statusCode: Int? = null,
    private val errors : HashMap<String?,ArrayList<String>?>? = null,
    private var message: String? = null) {

    private val type: ExceptionType


    fun getExceptionType() = type

    fun getMessage() = message

    fun getMessageFromErrors(): String? {
        if (errors?.isEmpty() == true)
            return message
        return errors?.values?.first()?.first() ?: message
    }

    init {
        type = getTypeFromThrowable()
        if (message == null) {
            message = when (type) {
                ExceptionType.SERVER_DOWN -> "SERVER_DOWN"
                ExceptionType.TIME_OUT -> "TIME_OUT"
                ExceptionType.UNAUTHORIZED -> "UNAUTHORIZED"
                ExceptionType.UNKNOWN -> "UNKNOWN"
                ExceptionType.HTTP -> "HTTP"
                ExceptionType.NETWORK -> "NETWORK"
                else -> ""
            }
        }

    }


    private fun getTypeFromThrowable(): ExceptionType{
        return when(throwable){

            is TimeoutException, is SocketTimeoutException -> ExceptionType.TIME_OUT
            is ConnectException -> ExceptionType.SERVER_DOWN
            is IOException, is UnknownHostException -> ExceptionType.NETWORK
            else -> ExceptionType.UNKNOWN
        }
    }

    public enum class ExceptionType{
        HTTP, //Status is not 200,
        NETWORK, //Internet Error
        UNKNOWN,
        SERVER_DOWN, //Server didn't respond properly
        TIME_OUT,
        UNAUTHORIZED
    }
}