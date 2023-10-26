package com.binamultimediaindonesia.waygoldendjaya.data.repository

import com.binamultimediaindonesia.waygoldendjaya.data.remote.WayGoldenDjayaApi
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.HomeScreenDto
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.HomeScreenDataRepository
import javax.inject.Inject

class HomeScreenDataRepositoryImpl @Inject constructor(
    private val api: WayGoldenDjayaApi
): HomeScreenDataRepository {
    override suspend fun getHomeScreenData(headers: Map<String,String>) : HomeScreenDto {
        return api.getHomeScreenData(headers)
    }
}