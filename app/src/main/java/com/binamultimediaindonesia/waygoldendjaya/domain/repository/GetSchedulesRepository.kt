package com.binamultimediaindonesia.waygoldendjaya.domain.repository

import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.SchedulesDto


interface GetSchedulesRepository {

   suspend fun getSchedule(headers:Map<String,String>, sessionId:String ):SchedulesDto

}