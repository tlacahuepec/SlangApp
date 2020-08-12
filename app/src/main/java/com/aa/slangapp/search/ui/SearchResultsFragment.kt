package com.aa.slangapp.com.aa.slangapp.search.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.aa.slangapp.com.aa.slangapp.dependencyInjection.Injectable
import com.aa.slangapp.com.aa.slangapp.dependencyInjection.injectViewModel
import com.aa.slangapp.databinding.FragmentSearchResultsBinding
import com.aa.slangapp.search.adapter.SearchResultsAdapter
import com.aa.slangapp.search.data.Result
import com.aa.slangapp.search.ui.SearchResultsViewModel
import javax.inject.Inject


class SearchResultsFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SearchResultsViewModel

    private val args: SearchResultsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        viewModel.term = args.term

        val binding = FragmentSearchResultsBinding.inflate(inflater, container, false)

        // Set the LifecycleOwner to be able to observe LiveData objects
        binding.lifecycleOwner = this
        // Bind ViewModel
        binding.viewmodel = viewModel

        val adapter = SearchResultsAdapter()
        binding.recyclerViewResults.adapter = adapter

        subscribeUi(binding, adapter)

        binding.buttonOrderByThumbsDown.setOnClickListener {
            viewModel.searchResults.observe(viewLifecycleOwner, Observer { result ->
                result.let {
                    Log.i("SearchResultsFragment", "searchResults")
                    when (result.status) {
                        Result.Status.SUCCESS -> {
                            binding.progressBar.hide()
                            result.data?.let { data ->
                                val sortedByThumbsDown = data.sortedByDescending { it.thumbsDown }
                                adapter.submitList(sortedByThumbsDown)
                            }
                        }
                        Result.Status.LOADING -> binding.progressBar.show()
                        Result.Status.ERROR -> {
                            binding.progressBar.hide()
                        }
                    }
                }
            })
        }

        binding.buttonOrderByThumbsUp.setOnClickListener {
            viewModel.searchResults.observe(viewLifecycleOwner, Observer { result ->
                result.let {
                    Log.i("SearchResultsFragment", "searchResults")
                    when (result.status) {
                        Result.Status.SUCCESS -> {
                            binding.progressBar.hide()
                            result.data?.let { data ->
                                val sortedByThumbsDown = data.sortedByDescending { it.thumbsUp }
                                adapter.submitList(sortedByThumbsDown)
                            }
                        }
                        Result.Status.LOADING -> binding.progressBar.show()
                        Result.Status.ERROR -> {
                            binding.progressBar.hide()
                        }
                    }
                }
            })
        }

        return binding.root
    }

    private fun subscribeUi(binding: FragmentSearchResultsBinding, adapter: SearchResultsAdapter) {
        viewModel.searchResults.observe(viewLifecycleOwner, Observer { result ->
            result.let {
                Log.i("SearchResultsFragment", "searchResults")
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
            }
        })
    }
}