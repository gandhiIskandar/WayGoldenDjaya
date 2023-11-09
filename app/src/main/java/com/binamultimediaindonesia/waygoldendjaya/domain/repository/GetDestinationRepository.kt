package com.binamultimediaindonesia.waygoldendjaya.domain.repository

import com.binamultimediaindonesia.waygoldendjaya.domain.model.Destination

interface GetDestinationRepository {

    suspend fun getDestinationDetail(header:Map<String, String>, id:String): Destination

}