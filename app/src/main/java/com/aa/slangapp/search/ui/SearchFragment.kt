package com.aa.slangapp.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.aa.slangapp.databinding.FragmentSearchBinding
import com.aa.slangapp.search.adapter.SearchResultsAdapter


class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels { SearchViewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchBinding.inflate(inflater, container, false)

        // Set the LifecycleOwner to be able to observe LiveData objects
        binding.lifecycleOwner = this
        // Bind ViewModel
        binding.viewmodel = viewModel

        binding.recyclerViewResults.adapter = SearchResultsAdapter()

        viewModel.searchResults.observe(viewLifecycleOwner, Observer {
            it?.let {
                // (binding.recyclerViewResults.adapter as SearchResultsAdapter).submitList(it)
            }
        })

        return binding.root
    }
}