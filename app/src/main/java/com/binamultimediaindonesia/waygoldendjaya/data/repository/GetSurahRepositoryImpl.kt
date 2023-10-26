package com.binamultimediaindonesia.waygoldendjaya.data.repository

import com.binamultimediaindonesia.waygoldendjaya.data.remote.AlquranApi
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.SurahDto
import com.binamultimediaindonesia.waygoldendjaya.domain.model.alquran.Surah
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.GetSurahRepository
import javax.inject.Inject

class GetSurahRepositoryImpl @Inject constructor(
    private val api:AlquranApi
):GetSurahRepository{

    override suspend fun getSurah(): SurahDto {
        return api.getSurat()
    }
}