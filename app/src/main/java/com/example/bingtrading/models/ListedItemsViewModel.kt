package com.example.bingtrading.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.bingtrading.repository.ListedItemsRepository

class ListedItemsViewModel : ViewModel() {
    private val repository = ListedItemsRepository()
    val listedItemsLiveData: LiveData<List<ListedItem>> = repository.listedItemLiveData
    val errorMessageLiveData: LiveData<String> = repository.errorMessageLiveData
    val updateMessageLiveData: LiveData<String> = repository.updateMessageLiveData

    init {
        reload()
    }

    fun reload() {
        repository.getAllListedItems()
    }

    operator fun get(index: Int): ListedItem? {
        return listedItemsLiveData.value?.get(index)
    }

    fun add(listedItem: ListedItem) {
        repository.add(listedItem)
    }

    fun delete(id: Int) {
        repository.delete(id)
    }

    fun sortByDescription() {
        repository.sortByDescription()
    }

    fun sortByTitleDescending() {
        repository.sortByDescriptionDescending()
    }

    fun sortByPrice() {
        repository.sortByPrice()
    }

    fun sortByPriceDescending() {
        repository.sortByPriceDescending()
    }

    fun filterByDescription(description: String) {
        repository.filterByDescription(description)
    }
}