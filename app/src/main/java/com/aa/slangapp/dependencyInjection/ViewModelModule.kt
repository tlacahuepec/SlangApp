package com.aa.slangapp.com.aa.slangapp.dependencyInjection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aa.slangapp.search.ui.SearchResultsViewModel
import com.aa.slangapp.search.ui.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchResultsViewModel::class)
    abstract fun bindSearchResultsViewModel(viewModel: SearchResultsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}