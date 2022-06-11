package com.upar24.cpsoft.data.remote

import com.upar24.cpsoft.data.remote.dto.UserDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {
    @GET("/user")
    suspend fun getListings() : Response<List<UserDto>>
}