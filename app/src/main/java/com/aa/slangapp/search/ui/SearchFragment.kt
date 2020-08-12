package com.aa.slangapp.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.aa.slangapp.com.aa.slangapp.search.ui.hideKeyboard
import com.aa.slangapp.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.buttonSearch.setOnClickListener {
            hideKeyboard()
            val direction =
                SearchFragmentDirections.actionSearchFragmentToSearchResultsFragment(binding.editTextSearch.text.toString())
            it.findNavController().navigate(direction)
        }

        return binding.root
    }
}