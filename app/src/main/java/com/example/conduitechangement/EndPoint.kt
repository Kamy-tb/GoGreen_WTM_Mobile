package com.example.conduitechangement

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface EndPoint {
    @GET("getuser/{nom}/{pwd}")
    suspend fun verifyUser(@Path ("nom")nom: String,@Path("pwd")pwd:String):Response<User>

    @POST("adduser")
    suspend fun adduser(@Body user: User) : Response<String>

    @GET("getfeeds")
    suspend fun getfeed():Response<List<Feed>>

    @GET("getranking")
    suspend fun getranking():Response<List<Ranking>>
}
