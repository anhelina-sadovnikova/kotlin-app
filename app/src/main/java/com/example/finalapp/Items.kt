package com.example.finalapp

import com.google.gson.annotations.SerializedName

data class Items(

    val userId: Int,
    val id: Int,
    val title: String,

    @SerializedName("body")
    val subtitle: String

) {
}