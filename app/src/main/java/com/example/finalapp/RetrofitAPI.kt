package com.example.finalapp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitAPI {

    @GET("pokemon")
    fun getPokemonList(@Query("limit") limit: Int = 20, @Query("offset") offset: Int = 0): Call<PokemonResponse>


}