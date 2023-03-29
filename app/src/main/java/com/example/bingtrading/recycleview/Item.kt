package com.example.bingtrading

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Item(
    val id: Int,
    val description: String,
    val price: Int,
    val sellerEmail: String,
    val sellerPhone: String,
    val time: Int,
    val pictureUrl: String
)

class ItemViewModel : ViewModel() {
    val ItemLiveData: MutableLiveData<Item> = MutableLiveData()
}


