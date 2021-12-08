package com.thechefz.e_commerce_demo.presentation_layer.fragments.home.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thechefz.e_commerce_demo.R
import com.thechefz.e_commerce_demo.data_layer.entities.OrderEntity
import kotlinx.android.synthetic.main.item_past_order.view.*

class PastOrderItemAdapter(val list:ArrayList<OrderEntity>) :RecyclerView.Adapter<PastOrderItemAdapter.ShoppingItemAdapterHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemAdapterHolder {
        return ShoppingItemAdapterHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_past_order, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ShoppingItemAdapterHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = list.size

    inner class ShoppingItemAdapterHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind() {

            val item = list[adapterPosition]
            itemView.tvSellerName.text = item.name
            itemView.tvPrice.text = item.price
            itemView.tvOrderCount.text = item.count

        }
    }
}