package com.example.finalapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalapp.databinding.ItemsBinding

class ItemsAdapter (var pokemonList : ArrayList<Pokemon>) : RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>() {

    inner class ItemsViewHolder(val adapterBinding:ItemsBinding)
        : RecyclerView.ViewHolder(adapterBinding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {

        val binding = ItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemsViewHolder(binding)

    }

    override fun getItemCount(): Int {

        return pokemonList.size

    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {

        holder.adapterBinding.textViewPokemonName.text = pokemonList[position].name

    }
}