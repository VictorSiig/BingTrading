package com.example.bingtrading

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.bingtrading.databinding.FragmentSecondBinding
import com.example.bingtrading.models.ListedItemsViewModel


class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private val listedItemsViewModel: ListedItemsViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = requireArguments()
        val secondFragmentArgs: SecondFragmentArgs = SecondFragmentArgs.fromBundle(bundle)
        val position = secondFragmentArgs.position
        val listedItem = listedItemsViewModel[position]

        if (listedItem == null) {
            binding.textviewMessage.text = "No item found"
            return
        }
        binding.textviewDescription.text = listedItem.description
        binding.textviewPrice.text = listedItem.price.toString()
        binding.textviewSellerEmail.text = listedItem.sellerEmail
        binding.textviewSellerPhone.text = listedItem.sellerPhone.toString()

        binding.homeButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}