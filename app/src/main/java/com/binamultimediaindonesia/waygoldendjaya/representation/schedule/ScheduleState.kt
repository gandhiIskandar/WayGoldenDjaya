package com.binamultimediaindonesia.waygoldendjaya.representation.schedule

import com.binamultimediaindonesia.waygoldendjaya.domain.model.Schedule
import com.binamultimediaindonesia.waygoldendjaya.domain.model.Streaming

data class ScheduleState (
    val isLoading:Boolean = false,
    val data: List<Schedule> = listOf(),
    val error:String =""

)