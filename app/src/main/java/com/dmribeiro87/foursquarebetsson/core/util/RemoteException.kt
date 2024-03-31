package com.dmribeiro87.foursquarebetsson.core.util

import retrofit2.HttpException
import retrofit2.Response

sealed class RemoteException(message: Response<Any>) : HttpException(message){
    class Error(response: Response<*>) : HttpException(response)
    class Generic(response: String) :  Exception(response)
}