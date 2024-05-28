package com.example.finalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.finalapp.databinding.ActivityPokemonApiBinding

class PokemonApiActivity : AppCompatActivity() {

    lateinit var apiBinding: ActivityPokemonApiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiBinding = ActivityPokemonApiBinding.inflate(layoutInflater)
        val view = apiBinding.root
        setContentView(view)
    }
}