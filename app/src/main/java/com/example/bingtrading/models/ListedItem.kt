package com.example.bingtrading.models

import java.io.Serializable
import kotlin.reflect.typeOf

data class ListedItem(
    val id: Int,
    val description: String,
    val price: Int,
    val sellerEmail: String,
    val sellerPhone: String,
    val time: Long,
    val pictureUrl: String,
    var displayType: String? = null
) : Serializable {

    constructor(
        description: String,
        price: Int,
        sellerEmail: String,
        sellerPhone: String,
        time: Long,
        pictureUrl: String
    ) : this(-1, description, price, sellerEmail, sellerPhone, time, pictureUrl)

    override fun toString(): String {
        return when (displayType) {
            "description" -> "$description"
            "price" -> "$price"
            else -> "$id, $description, $price, $sellerEmail,$sellerPhone, $time, $pictureUrl"
        }
        //return "$id, $description, $price, $sellerEmail,$sellerPhone, $time, $pictureUrl"
    }
}