package com.example.productlist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductListAdapter

    private val productListVM : ProductListVM by viewModels()

    private val step = 20
    private val total = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ProductListAdapter()
        val layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        binding.recyclerViewProducts.adapter = adapter
        binding.recyclerViewProducts.layoutManager = layoutManager

        productListVM.productList.observe(this){
            adapter.setProductList(it)
        }

        getProductList()
    }

    private fun getProductList(){
        var i = 0
        while(i < total) {
            productListVM.getProductList(i.toString(), step.toString())
            i += step
        }
    }
}