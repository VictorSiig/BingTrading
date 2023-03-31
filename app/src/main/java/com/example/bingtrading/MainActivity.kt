package com.example.bingtrading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.bingtrading.recycleview.Datasource
import com.example.bingtrading.recycleview.MyAdapter
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val listedItemList = Datasource(this).getItemsList()


        recyclerView.layoutManager = LinearLayoutManager(this)


        recyclerView.adapter = MyAdapter(listedItemList)
    }
}

