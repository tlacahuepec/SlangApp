package com.aa.slangapp.search.adapter

import androidx.recyclerview.widget.DiffUtil
import com.aa.slangapp.search.api.SearchResult


class SearchResultItemDifferenceCallback : DiffUtil.ItemCallback<SearchResult>() {

    override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
        return oldItem.id == newItem.id
    }


    override fun areContentsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
        return oldItem == newItem
    }

}