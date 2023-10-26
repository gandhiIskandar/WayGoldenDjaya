package com.binamultimediaindonesia.waygoldendjaya.domain.use_case.get_streaming_data

import com.binamultimediaindonesia.waygoldendjaya.common.Resource
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.HomeScreenDto
import com.binamultimediaindonesia.waygoldendjaya.domain.model.Streaming
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.GetStreamingDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetStreamingDataUseCase @Inject constructor(
    private val repository: GetStreamingDataRepository
) {
    operator fun invoke(headers : Map<String,String>) : Flow<Resource<Streaming>> = flow {
        try{
            emit(Resource.Loading<Streaming>())
            val streamingData = repository.getSteramingData(headers)
            emit(Resource.Success<Streaming>(streamingData))
        } catch(e: HttpException) {
            emit(Resource.Error<Streaming>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<Streaming>("Couldn't reach server. Check your internet connection."))
        }
    }
}