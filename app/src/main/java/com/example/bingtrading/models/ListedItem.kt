package com.example.bingtrading.models

data class ListedItem(
    val id: Int,
    val description: String,
    val price: Double,
    val sellerEmail: String,
    val sellerPhone: Int,
    val time: Long,
    val pictureUrl: String
) : java.io.Serializable {

    constructor(
        description: String,
        price: Double,
        sellerEmail: String,
        sellerPhone: Int,
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