package com.example.finalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalapp.databinding.ActivityPokemonApiBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class PokemonApiActivity : AppCompatActivity() {

    lateinit var apiBinding: ActivityPokemonApiBinding

    private val baseUrl = "https://jsonplaceholder.typicode.com/"

    var itemsList = ArrayList<Items>()

    lateinit var adapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiBinding = ActivityPokemonApiBinding.inflate(layoutInflater)
        val view = apiBinding.root
        setContentView(view)

        apiBinding.recyclerView.layoutManager = LinearLayoutManager(this)

        showItems()
    }

    fun showItems(){

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitAPI : RetrofitAPI = retrofit.create(RetrofitAPI::class.java)

        val call : Call<List<Items>> = retrofitAPI.getAllPosts()

        call.enqueue(object : Callback<List<Items>>{

            override fun onResponse(p0: Call<List<Items>>, p1: Response<List<Items>>) {

                if(p1.isSuccessful){

                    apiBinding.progressBarApi.isVisible = false
                    apiBinding.recyclerView.isVisible = true

                    itemsList = p1.body() as ArrayList<Items>
                    adapter = ItemsAdapter(itemsList)
                    apiBinding.recyclerView.adapter = adapter

                }

            }

            override fun onFailure(p0: Call<List<Items>>, p1: Throwable) {

                Toast.makeText(applicationContext,p1.localizedMessage,Toast.LENGTH_SHORT).show()

            }

        })

    }

}