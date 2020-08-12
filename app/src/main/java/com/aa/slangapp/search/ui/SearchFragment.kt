package com.aa.slangapp.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.aa.slangapp.com.aa.slangapp.dependencyInjection.Injectable
import com.aa.slangapp.com.aa.slangapp.dependencyInjection.injectViewModel
import com.aa.slangapp.databinding.FragmentSearchBinding
import javax.inject.Inject


class SearchFragment : Fragment(), Injectable {

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

        binding.buttonSearch.setOnClickListener {
            val direction =
                SearchFragmentDirections.actionSearchFragmentToSearchResultsFragment(binding.editTextSearch.text.toString())
            it.findNavController().navigate(direction)
        }

        return binding.root
    }
}