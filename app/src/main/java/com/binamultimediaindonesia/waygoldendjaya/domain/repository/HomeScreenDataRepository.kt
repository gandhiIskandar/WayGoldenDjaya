package com.binamultimediaindonesia.waygoldendjaya.domain.repository

import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.HomeScreenDto

interface HomeScreenDataRepository {

    suspend fun getHomeScreenData(headers:Map<String, String>):HomeScreenDto

}