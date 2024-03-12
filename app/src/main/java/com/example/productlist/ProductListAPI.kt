package com.example.productlist

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductListAPI {

    companion object{
        const val BASE_URL = "https://dummyjson.com/"
    }

    @GET("products")
    fun getProductList(@Query("skip") skip: String , @Query("limit") limit: String): Single<ProductGetResponse>
}