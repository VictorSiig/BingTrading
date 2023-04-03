package com.example.bingtrading.models

import java.io.Serializable

data class ListedItem(
    val id: Int,
    val description: String,
    val price: Double,
    val sellerEmail: String,
    val sellerPhone: Long,
    val time: Long,
    val pictureUrl: String
) : Serializable {

    constructor(
        description: String,
        price: Double,
        sellerEmail: String,
        sellerPhone: Long,
        time: Long,
        pictureUrl: String
    ) : this( -1, description, price, sellerEmail, sellerPhone, time, pictureUrl)

    override fun toString(): String {
     return "$id,$description,$price,$sellerEmail,$sellerPhone,$time"
    }
}

data class ListedItemShort(
    val description: String,
    val price: Double
)