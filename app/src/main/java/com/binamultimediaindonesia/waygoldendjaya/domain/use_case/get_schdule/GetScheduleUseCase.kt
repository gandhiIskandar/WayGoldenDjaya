package com.binamultimediaindonesia.waygoldendjaya.domain.use_case.get_schdule


import com.binamultimediaindonesia.waygoldendjaya.common.Resource
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.HomeScreenDto
import com.binamultimediaindonesia.waygoldendjaya.domain.model.Schedule
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.GetSchedulesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetScheduleUseCase @Inject constructor(
    private val repository: GetSchedulesRepository

) {

    operator fun invoke(headers : Map<String,String>, sessionId:String) : Flow<Resource<List<Schedule>>> = flow {
        try{
            emit(Resource.Loading<List<Schedule>>())
            val schedules = repository.getSchedule(headers, sessionId)

            emit(Resource.Success<List<Schedule>>(schedules))
        } catch(e: IOException) {
            emit(Resource.Error<List<Schedule>>("Error Getting Data"))
        } catch (e :Exception){
            emit(Resource.Error<List<Schedule>>(e.message?:"Unexpected Error"))
        }
    }

}