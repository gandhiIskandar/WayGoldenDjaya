package com.binamultimediaindonesia.waygoldendjaya.domain.repository

import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.SurahDto


interface GetSurahRepository {

    suspend fun getSurah():SurahDto

}