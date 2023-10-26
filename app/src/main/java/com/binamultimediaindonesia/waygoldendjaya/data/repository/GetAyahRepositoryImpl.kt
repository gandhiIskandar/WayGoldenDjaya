package com.binamultimediaindonesia.waygoldendjaya.data.repository

import com.binamultimediaindonesia.waygoldendjaya.data.remote.AlquranApi
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.AyahDto
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.GetAyahRepository
import javax.inject.Inject

class GetAyahRepositoryImpl @Inject constructor(
    private val api:AlquranApi
):GetAyahRepository{
    override suspend fun getAyah(nomor:String): AyahDto  = api.getAyat(nomor)
}