package com.example.bingtrading.recycleview

import android.content.Context
import com.example.bingtrading.R

class Datasource(val context: Context) {
    fun getItemsList(): Array<String> {
        return context.resources.getStringArray(R.array.items_array)
    }
}