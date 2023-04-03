package com.example.bingtrading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.bingtrading.databinding.ActivityMainBinding
import com.example.bingtrading.models.ListedItemsViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val listedItemsViewModel: ListedItemsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listedItemsViewModel.updateMessageLiveData.observe(this) { message ->
            Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
        }
    }
}

