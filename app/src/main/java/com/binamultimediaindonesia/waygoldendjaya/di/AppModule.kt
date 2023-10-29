package com.binamultimediaindonesia.waygoldendjaya.di

import android.content.Context
import com.binamultimediaindonesia.waygoldendjaya.data.remote.AlquranApi
import com.binamultimediaindonesia.waygoldendjaya.data.remote.WayGoldenDjayaApi
import com.binamultimediaindonesia.waygoldendjaya.data.repository.*
import com.binamultimediaindonesia.waygoldendjaya.datastore.abstraction.StoreUserDataRepository
import com.binamultimediaindonesia.waygoldendjaya.datastore.implementation.StoreUserDataImpl
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providerWayGoldenDjayaApi(): WayGoldenDjayaApi {
        return Retrofit.Builder()
            .baseUrl("https://admin.waygoldendjaya.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WayGoldenDjayaApi::class.java)
    }


    @Provides
    @Singleton
    fun providerAlquranpi(): AlquranApi {
        return Retrofit.Builder()
            .baseUrl("https://equran.id")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AlquranApi::class.java)
    }




    @Provides
    @Singleton
    fun providerUserRepository(api: WayGoldenDjayaApi): UserRepository {
        return UserRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providerStoreUserRepository(@ApplicationContext context: Context): StoreUserDataRepository {
        //masukan data token ke datastore
        return StoreUserDataImpl(context)
    }

    @Provides
    @Singleton
    fun providerHomeSreenDataRepository(api: WayGoldenDjayaApi):HomeScreenDataRepository{
        return HomeScreenDataRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providerGetSchedulesRepository(api: WayGoldenDjayaApi):GetSchedulesRepository{
        return GetSchedulesImpl(api)
    }

    @Provides
    @Singleton
    fun providerGetStreamingDataRepository(api: WayGoldenDjayaApi):GetStreamingDataRepository{
        return GetStreamingDataImpl(api)
    }

    @Provides
    @Singleton
    fun providerGetSurahRepository(api : AlquranApi):GetSurahRepository{
        return GetSurahRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providerGetAyahRepository(api: AlquranApi):GetAyahRepository{
        return GetAyahRepositoryImpl(api)
    }




}