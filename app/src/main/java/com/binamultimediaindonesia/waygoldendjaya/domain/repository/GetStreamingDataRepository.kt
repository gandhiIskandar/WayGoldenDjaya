package com.binamultimediaindonesia.waygoldendjaya.domain.repository

import com.binamultimediaindonesia.waygoldendjaya.domain.model.Streaming

interface GetStreamingDataRepository {

    suspend fun getSteramingData(headers:Map<String,String>, groupId:String ):Streaming

}