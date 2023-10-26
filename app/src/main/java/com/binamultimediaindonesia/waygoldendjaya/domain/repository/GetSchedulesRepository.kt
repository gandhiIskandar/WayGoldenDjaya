package com.binamultimediaindonesia.waygoldendjaya.domain.repository

import com.binamultimediaindonesia.waygoldendjaya.domain.model.Schedule

interface GetSchedulesRepository {

   suspend fun getSchedule(headers:Map<String,String>, sessionId:String ):List<Schedule>

}