package com.example.bingtrading.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bingtrading.R

class MyAdapterProfile<T : ListedItem>(
    private val itemList: List<T>,
    private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<MyAdapterProfile.ItemViewHolder>() {

    override fun getItemCount(): Int {
        return itemList.size
    }

    private var onDeleteItemClickListener: ((ListedItem) -> Unit)? = null

    // Add this function to set the delete item click listener
    fun setOnDeleteItemClickListener(listener: (ListedItem) -> Unit) {
        onDeleteItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.profile_item_card, parent, false)

        return ItemViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position], onItemClicked, onDeleteItemClickListener)

        val item = itemList[position]
        item.displayType = "description"
        holder.profileItemDescriptionTextView.text = when (item.displayType) {
            "description" -> item.description
            "price" -> ""
            else -> item.toString()
        }
        item.displayType = "price"
        holder.profileItemPriceTextView.text = when (item.displayType) {
            "description" -> ""
            "price" -> item.price.toString()
            else -> ""
        }
    }

    class ItemViewHolder(itemView: View, private val onItemClicked: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val deleteItemButton: ImageButton = itemView.findViewById(R.id.delete_item_button)
        fun bind(
            item: ListedItem,
            onItemClicked: (Int) -> Unit,
            onDeleteItemClickListener: ((ListedItem) -> Unit)?
        ) {
            deleteItemButton.setOnClickListener {
                onDeleteItemClickListener?.invoke(item)
            }
        }

        val profileItemDescriptionTextView: TextView =
            itemView.findViewById(R.id.profile_item_description_text_view)
        val profileItemPriceTextView: TextView =
            itemView.findViewById(R.id.profile_item_price_text_view)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = bindingAdapterPosition
            onItemClicked(position)
        }
    }
}