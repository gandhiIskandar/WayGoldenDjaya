package com.binamultimediaindonesia.waygoldendjaya.domain.use_case.get_destination

import com.binamultimediaindonesia.waygoldendjaya.common.Resource
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.LoginDto
import com.binamultimediaindonesia.waygoldendjaya.domain.model.Destination
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.GetDestinationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetDestinationUseCase @Inject constructor(
    val repository: GetDestinationRepository
){

    operator fun invoke(header:Map<String,String>, id:String): Flow<Resource<Destination>> = flow {
        try {
            emit(Resource.Loading<Destination>())
            val data = repository.getDestinationDetail(header, id)
            emit(Resource.Success<Destination>(data))
        } catch (e: IOException) {
            emit(Resource.Error<Destination>("Error Getting Data"))
        } catch (e: Exception) {
            emit(Resource.Error<Destination>(e.message ?: "Unexpected Error"))
        }
    }


}