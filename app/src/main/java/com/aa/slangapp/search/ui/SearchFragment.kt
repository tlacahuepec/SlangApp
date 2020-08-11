package com.aa.slangapp.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aa.slangapp.com.aa.slangapp.dependencyInjection.Injectable
import com.aa.slangapp.com.aa.slangapp.dependencyInjection.injectViewModel
import com.aa.slangapp.com.aa.slangapp.search.ui.hide
import com.aa.slangapp.com.aa.slangapp.search.ui.show
import com.aa.slangapp.databinding.FragmentSearchBinding
import com.aa.slangapp.search.adapter.SearchResultsAdapter
import com.aa.slangapp.search.data.Result
import javax.inject.Inject


class SearchFragment : Fragment(), Injectable {

    // TODO: hilt
    //private val viewModel: SearchViewModel by viewModels { SearchViewModelFactory }
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = injectViewModel(viewModelFactory)

        val binding = FragmentSearchBinding.inflate(inflater, container, false)

        // Set the LifecycleOwner to be able to observe LiveData objects
        binding.lifecycleOwner = this
        // Bind ViewModel
        binding.viewmodel = viewModel

        val adapter = SearchResultsAdapter()
        binding.recyclerViewResults.adapter = adapter

        subscribeUi(binding, adapter)

        return binding.root
    }

    private fun subscribeUi(binding: FragmentSearchBinding, adapter: SearchResultsAdapter) {
        viewModel.searchResults.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    result.data?.let { adapter.submitList(it) }
                }
                Result.Status.LOADING -> binding.progressBar.show()
                Result.Status.ERROR -> {
                    binding.progressBar.hide()
                }
            }
        })
    }//ashbeen group president abbott diabetes care medical devices
}