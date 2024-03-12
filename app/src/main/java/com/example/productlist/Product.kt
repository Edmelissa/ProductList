package com.example.productlist

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id : Int,

    @SerializedName("title")
    val title : String,

    @SerializedName("description")
    val description : String,

    @SerializedName("thumbnail")
    val thumbnail : String
)
