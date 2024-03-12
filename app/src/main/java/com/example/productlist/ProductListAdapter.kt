package com.example.productlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productlist.databinding.ProductItemBinding

class ProductListAdapter : RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    private val productList = mutableListOf<Product>()

    fun setProductList(productList : List<Product>){
        this.productList.addAll(productList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(layoutInflater, parent, false)

        return ProductListViewHolder(binding)
    }

    override fun getItemCount() = productList.size

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) = holder.onBind(productList[position])

    class ProductListViewHolder(binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val title: TextView = binding.textViewProductTitle
        private val description: TextView = binding.textViewProductDescription
        private val thumbnail: ImageView = binding.imageViewProductImage

        fun onBind(product: Product) {
            title.text = product.title
            description.text = product.description

            Glide.with(this.itemView.context)
                .load(product.thumbnail)
                .error(R.drawable.ic_launcher_background)
                .circleCrop()
                .into(thumbnail)
        }
    }
}