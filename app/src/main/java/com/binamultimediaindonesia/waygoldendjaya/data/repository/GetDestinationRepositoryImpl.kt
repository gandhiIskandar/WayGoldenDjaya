package com.binamultimediaindonesia.waygoldendjaya.data.repository

import com.binamultimediaindonesia.waygoldendjaya.data.remote.WayGoldenDjayaApi
import com.binamultimediaindonesia.waygoldendjaya.domain.model.Destination
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.GetDestinationRepository
import javax.inject.Inject

class GetDestinationRepositoryImpl @Inject constructor(
    val api:WayGoldenDjayaApi
) :GetDestinationRepository {
    override suspend fun getDestinationDetail(header: Map<String, String>, id: String): Destination {
      return  api.getDestination(header, id)
    }
}