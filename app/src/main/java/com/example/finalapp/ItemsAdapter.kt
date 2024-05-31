package com.example.finalapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalapp.databinding.ItemsBinding
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class ItemsAdapter(
    var pokemonList: ArrayList<Pokemon>,
    private val retrofitAPI: RetrofitAPI
) : RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>() {

    inner class ItemsViewHolder(val adapterBinding: ItemsBinding) :
        RecyclerView.ViewHolder(adapterBinding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {

        val binding = ItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemsViewHolder(binding)

    }

    override fun getItemCount(): Int {

        return pokemonList.size

    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.adapterBinding.textViewPokemonName.text = pokemon.name

        // Fetch and display sprite
        retrofitAPI.getPokemonDetail(pokemon.name).enqueue(object : Callback<PokemonDetail> {
            override fun onResponse(call: Call<PokemonDetail>, response: Response<PokemonDetail>) {
                if (response.isSuccessful) {
                    val pokemonDetail = response.body()
                    if (pokemonDetail != null) {
                        val spriteUrl = pokemonDetail.sprites.front_default
                        if (!spriteUrl.isNullOrEmpty()) {
                            Glide.with(holder.itemView.context)
                                .load(spriteUrl)
                                .into(holder.adapterBinding.imageViewPokemon)
                        }
                    }
                }
            }

            override fun onFailure(p0: Call<PokemonDetail>, p1: Throwable) {
                TODO("Not yet implemented")
            }
        })
        // Fetch and display details
        retrofitAPI.getPokemonDetail(pokemon.name).enqueue(object : Callback<PokemonDetail> {
            override fun onResponse(call: Call<PokemonDetail>, response: Response<PokemonDetail>) {
                if (response.isSuccessful) {
                    val pokemonDetail = response.body()
                    if (pokemonDetail != null) {
                        holder.adapterBinding.height.text = "${pokemonDetail.height}"
                        holder.adapterBinding.weight.text = "${pokemonDetail.weight}"
                        holder.adapterBinding.abilities.text = "${pokemonDetail.abilities.joinToString { it.ability.name }}"
                        holder.adapterBinding.types.text = "${pokemonDetail.types.joinToString { it.type.name }}"


                        val spriteUrl = pokemonDetail.sprites.front_default
                        if (!spriteUrl.isNullOrEmpty()) {
                            Glide.with(holder.itemView.context)
                                .load(spriteUrl)
                                .into(holder.adapterBinding.imageViewPokemon)
                        }
                    }
                }
            }

            override fun onFailure(p0: Call<PokemonDetail>, p1: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}