package com.aa.slangapp.search.adapter

import androidx.recyclerview.widget.DiffUtil
import com.aa.slangapp.search.model.SearchResult

class SearchResultItemDifferenceCallback : DiffUtil.ItemCallback<SearchResult>() {

    override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
        return oldItem.defid == newItem.defid
    }


    override fun areContentsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
        return oldItem == newItem
    }

}