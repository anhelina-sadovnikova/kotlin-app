package com.example.finalapp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitAPI {

    @GET("/posts")
    fun getAllPosts() : Call<List<Items>>

}