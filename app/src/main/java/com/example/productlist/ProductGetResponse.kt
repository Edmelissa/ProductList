package com.example.productlist

import com.google.gson.annotations.SerializedName

data class ProductGetResponse(
    @SerializedName("products")
    val products : List<Product>,

    @SerializedName("total")
    val total : Int,

    @SerializedName("skip")
    val skip : Int,

    @SerializedName("limit")
    val limit : Int,
)
