package com.aa.slangapp.com.aa.slangapp.dependencyInjection

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DictionaryClient

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CoroutineScropeIO