package com.binamultimediaindonesia.waygoldendjaya.data.repository

import com.binamultimediaindonesia.waygoldendjaya.data.remote.WayGoldenDjayaApi
import com.binamultimediaindonesia.waygoldendjaya.domain.model.Streaming
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.GetStreamingDataRepository
import javax.inject.Inject

class GetStreamingDataImpl @Inject constructor(
    private val api: WayGoldenDjayaApi
):GetStreamingDataRepository {

    override suspend fun getSteramingData(headers: Map<String, String>): Streaming {
     return  api.getStreamingData(headers)
    }
}