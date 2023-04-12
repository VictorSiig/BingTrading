package com.example.bingtrading

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bingtrading.databinding.FragmentFirstBinding
import com.example.bingtrading.models.ListedItemsViewModel
import com.example.bingtrading.models.MyAdapter

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val listedItemsViewModel: ListedItemsViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listedItemsViewModel.listedItemsLiveData.observe(viewLifecycleOwner) { items ->
            binding.recyclerView.visibility = if (items == null) View.GONE else View.VISIBLE
            if (items != null) {
                val adapter = MyAdapter(items) { position ->
                    val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(position)
                    findNavController().navigate(action)
                }
                var columns = 1
                val currentOrientation = this.resources.configuration.orientation
                if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                    columns = 2
                } else if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
                    columns = 1
                }

                binding.recyclerView.layoutManager = GridLayoutManager(this.context, columns)

                binding.recyclerView.adapter = adapter
            }
        }

        listedItemsViewModel.errorMessageLiveData.observe(viewLifecycleOwner) {errorMessage ->
            binding.textviewMessage.text = errorMessage
        }

        listedItemsViewModel.reload()

        binding.swiperefresh.setOnRefreshListener {
            listedItemsViewModel.reload()
            binding.swiperefresh.isRefreshing = false
        }

        binding.buttonSort.setOnClickListener {
            when (binding.spinnerSorting.selectedItemPosition) {
                0 -> listedItemsViewModel.sortByTitle()
                1 -> listedItemsViewModel.sortByTitleDescending()
                2 -> listedItemsViewModel.sortByPrice()
                3 -> listedItemsViewModel.sortByPriceDescending()
            }
        }

        binding.profileButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_fourthFragment)
        }

        binding.createListingButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_fifthFragment)
        }
    }
}