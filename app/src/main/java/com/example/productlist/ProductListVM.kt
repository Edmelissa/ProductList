package com.example.productlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

class ProductListVM : ViewModel() {

    private val _productList = MutableLiveData<List<Product>>()
    var productList: LiveData<List<Product>> = _productList

    private val gsonBuilder = GsonBuilder()
        .setLenient()
        .setPrettyPrinting()
        .create()

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(35, TimeUnit.SECONDS)
        .readTimeout(35, TimeUnit.SECONDS)
        .addNetworkInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(ProductListAPI.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    private val productListAPI = retrofit.create<ProductListAPI>()

    private val compositeDisposable = CompositeDisposable()

    fun getProductList(skip: String, limit: String) = compositeDisposable.add(
        productListAPI.getProductList(skip = skip, limit = limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.products }
            .subscribe(_productList::postValue) {
                Log.d("error", "error", it)
            }
    )

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}