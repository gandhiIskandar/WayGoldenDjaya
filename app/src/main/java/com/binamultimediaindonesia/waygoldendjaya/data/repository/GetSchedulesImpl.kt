package com.binamultimediaindonesia.waygoldendjaya.data.repository

import com.binamultimediaindonesia.waygoldendjaya.data.remote.WayGoldenDjayaApi
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.SchedulesDto
import com.binamultimediaindonesia.waygoldendjaya.domain.model.Schedule
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.GetSchedulesRepository

import javax.inject.Inject

class GetSchedulesImpl @Inject constructor(
    private val api:WayGoldenDjayaApi
):GetSchedulesRepository {
    override suspend fun getSchedule(headers: Map<String, String>, sessionId: String):SchedulesDto {

        return api.getSchedules(headers, sessionId)

    }
}