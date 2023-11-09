package com.binamultimediaindonesia.waygoldendjaya.domain.use_case.get_schdules


import com.binamultimediaindonesia.waygoldendjaya.common.Resource
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.SchedulesDto
import com.binamultimediaindonesia.waygoldendjaya.domain.model.Schedule
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.GetSchedulesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetSchedulesUseCase @Inject constructor(
    private val repository: GetSchedulesRepository

) {

    operator fun invoke(headers : Map<String,String>, sessionId:String) : Flow<Resource<SchedulesDto>> = flow {
        try{
            emit(Resource.Loading<SchedulesDto>())
            val schedules = repository.getSchedule(headers, sessionId)

            emit(Resource.Success<SchedulesDto>(schedules))
        } catch(e: IOException) {
            emit(Resource.Error<SchedulesDto>("Error Getting Data"))
        } catch (e :Exception){
            emit(Resource.Error<SchedulesDto>(e.message?:"Unexpected Error"))
        }
    }

}