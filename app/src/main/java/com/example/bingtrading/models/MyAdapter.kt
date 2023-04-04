package com.example.bingtrading.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bingtrading.R

class MyAdapter<T : ListedItem>(
    private val itemList: List<T>,
    private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<MyAdapter.ItemViewHolder>() {
    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.listed_item_card, parent, false)

        return ItemViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        item.displayType = "description"
        holder.listedItemDescriptionTextView.text = when (item.displayType) {
            "description" -> item.description
            "price" -> ""
            else -> item.toString()
        }
        item.displayType = "price"
        holder.listedItemPriceTextView.text = when (item.displayType) {
            "description" -> ""
            "price" -> item.price.toString()
            else -> ""
        }
    }

    class ItemViewHolder(itemView: View, private val onItemClicked: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val listedItemDescriptionTextView: TextView = itemView.findViewById(R.id.listed_item_description_text_view)
        val listedItemPriceTextView: TextView = itemView.findViewById(R.id.listed_item_price_text_view)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = bindingAdapterPosition

            onItemClicked(position)
        }
    }
}