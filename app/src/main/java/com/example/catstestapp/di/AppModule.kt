package com.example.catstestapp.di

import android.app.Application
import androidx.room.Room
import com.example.catstestapp.data.local.CatFavoritesDatabase
import com.example.catstestapp.data.remote.CatsApiService
import com.example.catstestapp.data.repository.CatRepositoryImpl
import com.example.catstestapp.data.repository.factory.CatPagingSourceFactory
import com.example.catstestapp.domain.repository.CatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BASE_URL = "https://api.thecatapi.com/"

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideCatFavoritesDatabase(application: Application): CatFavoritesDatabase {
        return Room.databaseBuilder(application, CatFavoritesDatabase::class.java, "cats")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): CatsApiService =
        retrofit.create(CatsApiService::class.java)

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        factory: CatPagingSourceFactory
    ): CatRepository {
        return CatRepositoryImpl(factory)
    }
}
