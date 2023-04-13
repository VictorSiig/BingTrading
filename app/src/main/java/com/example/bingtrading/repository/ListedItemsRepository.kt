package com.example.bingtrading.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.bingtrading.models.ListedItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListedItemsRepository {
    private val url = "https://anbo-salesitems.azurewebsites.net/api/"

    private val bingTradingService: BingTradingService
    val listedItemLiveData: MutableLiveData<List<ListedItem>> = MutableLiveData<List<ListedItem>>()
    val errorMessageLiveData: MutableLiveData<String> = MutableLiveData()
    val updateMessageLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        val build: Retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        bingTradingService = build.create(BingTradingService::class.java)
        getAllListedItems()
    }

    fun getAllListedItems() {
        bingTradingService.getAllListedItems().enqueue(object : Callback<List<ListedItem>> {
            override fun onResponse(call: Call<List<ListedItem>>, response: Response<List<ListedItem>>) {
                if (response.isSuccessful) {
                    val b: List<ListedItem>? = response.body()
                    listedItemLiveData.postValue(b!!)
                    errorMessageLiveData.postValue("")
                } else {
                    val message = response.code().toString() + " " + response.message()
                    errorMessageLiveData.postValue(message)
                    Log.d("APPLE", message)
                }
            }

            override fun onFailure(call: Call<List<ListedItem>>, t: Throwable) {
                errorMessageLiveData.postValue(t.message)
                Log.d("APPLE", t.message!!)
            }
        })
    }

    fun add(listedItem: ListedItem) {
        bingTradingService.listItem(listedItem).enqueue(object : Callback<ListedItem> {
            override fun onResponse(call: Call<ListedItem>, response: Response<ListedItem>) {
                if (response.isSuccessful) {
                    Log.d("APPLE", "Added: " + response.body())
                    updateMessageLiveData.postValue("Added: " + response.body())
                    getAllListedItems()
                } else {
                    val message = response.code().toString() + " " + response.message()
                    errorMessageLiveData.postValue(message)
                    Log.d("APPLE", message)
                }
            }

            override fun onFailure(call: Call<ListedItem>, t: Throwable) {
                errorMessageLiveData.postValue(t.message)
                Log.d("APPLE", t.message!!)
            }
        })
    }

    fun delete(id: Int) {
        bingTradingService.deleteListedItem(id).enqueue(object : Callback<ListedItem> {
            override fun onResponse(call: Call<ListedItem>, response: Response<ListedItem>) {
                if (response.isSuccessful) {
                    Log.d("APPLE", "Updated: " + response.body())
                    updateMessageLiveData.postValue("Deleted: " + response.body())
                    getAllListedItems()
                } else {
                    val message = response.code().toString() + " " + response.message()
                    errorMessageLiveData.postValue(message)
                    Log.d("APPLE", message)
                }
            }

            override fun onFailure(call: Call<ListedItem>, t: Throwable) {
                errorMessageLiveData.postValue(t.message)
                Log.d("APPLE", t.message!!)
            }
        })
    }

    fun sortByDescription() {
        listedItemLiveData.value = listedItemLiveData.value?.sortedBy { it.description }
    }

    fun sortByDescriptionDescending() {
        listedItemLiveData.value = listedItemLiveData.value?.sortedByDescending { it.description }
    }

    fun sortByPrice() {
        listedItemLiveData.value = listedItemLiveData.value?.sortedBy { it.price }
    }

    fun sortByPriceDescending() {
        listedItemLiveData.value = listedItemLiveData.value?.sortedByDescending { it.price }
    }

    fun filterByDescription(description: String) {
        if (description.isBlank()) {
            getAllListedItems()
        }else {
            listedItemLiveData.value = listedItemLiveData.value?.filter { listedItem -> listedItem.description.contains(description) }
            // TODO: bug fix: listedItemLiveData.value keeps getting smaller for each filter
        }
    }
}