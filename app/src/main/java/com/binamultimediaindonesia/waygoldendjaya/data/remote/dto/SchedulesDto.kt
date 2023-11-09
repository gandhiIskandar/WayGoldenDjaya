package com.binamultimediaindonesia.waygoldendjaya.data.remote.dto

import com.binamultimediaindonesia.waygoldendjaya.domain.model.Schedule

data class SchedulesDto (
    val date_now:String="",
    val schedules:List<Schedule> = listOf()
        )