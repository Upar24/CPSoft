package com.upar24.cpsoft.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET

interface UserApi {
    @GET("/user")
    suspend fun getListings() : ResponseBody
}