package com.example.bingtrading.repository

import com.example.bingtrading.models.ListedItem
import retrofit2.Call
import retrofit2.http.*

interface BingTradingService {
    @GET("SalesItems")
    fun getAllListedItems(): Call<List<ListedItem>>

    @POST("SalesItems")
    fun listItem(@Body listedItem: ListedItem): Call<ListedItem>

    @DELETE("SalesItems")
    fun deleteListedItem(@Path("id") id: Int): Call<ListedItem>
}