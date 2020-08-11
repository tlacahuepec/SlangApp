package com.aa.slangapp.com.aa.slangapp.dependencyInjection


import android.app.Application
import com.aa.slangapp.com.aa.slangapp.search.localData.AppDatabase
import com.aa.slangapp.dependencyInjection.ServiceModule
import com.aa.slangapp.search.api.DictionaryService
import com.aa.slangapp.search.api.DictionaryService.Companion.BASE_URL
import com.aa.slangapp.search.data.DictionaryDataSource
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, ServiceModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideDictionaryService(
        @DictionaryClient okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) =
        provideService(okHttpClient, converterFactory, DictionaryService::class.java)


    @DictionaryClient
    @Provides
    fun providePrivateOkHttpClient(
        upstreamClient: OkHttpClient
    ): OkHttpClient {
        return upstreamClient
            .newBuilder()
            .build()
    }

    @Singleton
    @Provides
    fun provideDictionaryDataSource(dictionaryService: DictionaryService) =
        DictionaryDataSource(dictionaryService)

    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideDictionaryDao(db: AppDatabase) = db.dictionaryDao()

    @CoroutineScropeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

    private fun buildRetrofitInstance(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    private fun <T> provideService(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory,
        clazz: Class<T>
    ): T {
        return buildRetrofitInstance(okHttpClient, converterFactory)
            .create(clazz)
    }
}