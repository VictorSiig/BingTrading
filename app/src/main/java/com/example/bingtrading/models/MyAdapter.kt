package com.example.bingtrading.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bingtrading.R

class MyAdapter<T>(
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
        // holder.bind(itemList[position])
        holder.listedItemTextView.text = itemList[position].toString()

    }

    class ItemViewHolder(itemView: View, private val onItemClicked: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val listedItemTextView: TextView = itemView.findViewById(R.id.listed_item_text_view)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = bindingAdapterPosition

            onItemClicked(position)
        }
    }
}