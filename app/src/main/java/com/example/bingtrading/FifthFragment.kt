package com.example.bingtrading

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.bingtrading.databinding.FragmentFifthBinding
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
        val description: String = binding.editTextDescription.toString()
        val price: Double = binding.editTextPrice.toString().toDouble()
        val pictureUrl: String = binding.editTextPictureUrl.toString()
        val email: String = binding.editTextEmail.toString()
        val phone: Long = binding.editTextPhone.toString().toLong()
        val time: Long = System.currentTimeMillis()

        binding.addListingButton.setOnClickListener{
            TODO()
        }
        binding.homeButton.setOnClickListener {
            findNavController().navigate(R.id.action_fifthFragment_to_FirstFragment)
        }
        binding.profileButton.setOnClickListener {
            findNavController().navigate(R.id.action_fifthFragment_to_fourthFragment)
        }
    }
}