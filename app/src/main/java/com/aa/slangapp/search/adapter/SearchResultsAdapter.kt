package com.aa.slangapp.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aa.slangapp.databinding.SearchResultListItemBinding
import com.aa.slangapp.search.model.SearchResult

class SearchResultsAdapter :
    ListAdapter<SearchResult, SearchResultsAdapter.ViewHolder>(SearchResultItemDifferenceCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchResultsAdapter.ViewHolder {
        return ViewHolder(
            SearchResultListItemBinding.inflate(
                LayoutInflater.from(parent.context)
                , parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchResultsAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(private val binding: SearchResultListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchResult) {
            binding.searchResult = item
        }

    }

}