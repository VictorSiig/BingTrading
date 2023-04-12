package com.example.bingtrading

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.bingtrading.databinding.FragmentFifthBinding
import com.example.bingtrading.models.ListedItem
import com.example.bingtrading.models.ListedItemsViewModel

class FifthFragment : Fragment() {
    private var _binding: FragmentFifthBinding? = null
    private val binding get() = _binding!!
    private val listedItemsViewModel: ListedItemsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFifthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.addListingButton.setOnClickListener{
            val description = binding.editTextDescription.text.toString()
            var price = 0
            val sellerEmail = binding.editTextEmail.text.toString()
            val sellerPhone = binding.editTextPhone.text.toString()
            val time = System.currentTimeMillis()/1000
            val pictureUrl = binding.editTextPictureUrl.text.toString()

            val priceText = binding.editTextPrice.text.toString()
            if(priceText.isNotEmpty()) price = priceText.toInt() else 0

            val newListing = ListedItem(description,price,sellerEmail,sellerPhone,time,pictureUrl)

            listedItemsViewModel.add(newListing)

            findNavController().navigate(R.id.action_fifthFragment_to_fourthFragment)
        }
        binding.homeButton.setOnClickListener {
            findNavController().navigate(R.id.action_fifthFragment_to_FirstFragment)
        }
        binding.profileButton.setOnClickListener {
            findNavController().navigate(R.id.action_fifthFragment_to_fourthFragment)
        }
    }
}