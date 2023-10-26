package com.binamultimediaindonesia.waygoldendjaya.data.remote

import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.AyahDto
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.SurahDto
import retrofit2.http.GET
import retrofit2.http.Path

interface AlquranApi {

    @GET("/api/v2/surat")
    suspend fun getSurat():SurahDto

    @GET("/api/v2/surat/{number}")

    suspend fun getAyat(@Path("number") number:String):AyahDto



}