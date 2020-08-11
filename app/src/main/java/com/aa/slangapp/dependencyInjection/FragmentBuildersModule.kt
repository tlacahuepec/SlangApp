package com.aa.slangapp.com.aa.slangapp.dependencyInjection

import com.aa.slangapp.search.ui.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): SearchFragment

}
