package com.mvvmCoroutineKotlin.networking

import com.mvvmCoroutineKotlin.model.AppResponse
import com.mvvmCoroutineKotlin.roomDB.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkService(private val networkRequest: NetworkRequest) {

    /*If you want to pass params as json request then use below
    suspend fun requestLogin(loginRequest: LoginRequest): AppResponse<Login> = withContext(Dispatchers.Default) {
    networkRequest.requestLogin(loginRequest).await()
    }
    */

    /*If you want to pass params as a form data request*/
    suspend fun requestLogin(email: String, password: String): AppResponse<User> = withContext(Dispatchers.IO) {
        networkRequest.requestLoginAsync(email, password).await()
    }

    suspend fun requestLogout(): AppResponse<Any> = withContext(Dispatchers.IO) {
        networkRequest.requestLogout().await()
    }
}
