package com.thechefz.e_commerce_demo.presentation_layer.fragments.home.product_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thechefz.e_commerce_demo.R
import com.thechefz.e_commerce_demo.data_layer.entities.CategoryEntity
import com.thechefz.e_commerce_demo.data_layer.entities.OrderEntity
import com.thechefz.e_commerce_demo.data_layer.entities.ProductEntity
import com.thechefz.e_commerce_demo.utils.extensions.showImage
import kotlinx.android.synthetic.main.item_category.view.*

class ProductAdapter(var list:ArrayList<ProductEntity>,val selectedItem:(ProductEntity)->Unit) :RecyclerView.Adapter<ProductAdapter.ShoppingItemAdapterHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemAdapterHolder {
        return ShoppingItemAdapterHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ShoppingItemAdapterHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = list.size

    inner class ShoppingItemAdapterHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener {
                selectedItem.invoke(list[adapterPosition])
            }
        }
        fun bind() {
            val item = list[adapterPosition]
            itemView.tvName.text = item.name
            itemView.ivItem.showImage(item.img)

        }
    }
}