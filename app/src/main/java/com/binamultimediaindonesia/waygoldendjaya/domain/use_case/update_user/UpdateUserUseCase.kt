package com.binamultimediaindonesia.waygoldendjaya.domain.use_case.update_user

import com.binamultimediaindonesia.waygoldendjaya.common.Resource
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.ResponseDto
import com.binamultimediaindonesia.waygoldendjaya.domain.model.User
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val repository: UserRepository
){

    operator fun invoke(id:String, fieldMap: Map<String, Any>, headerMap: Map<String,String> ) : Flow<Resource<ResponseDto>> = flow {
        try{
            emit(Resource.Loading<ResponseDto>())
            val message = repository.updateUser(id, fieldMap, headerMap)
            emit(Resource.Success<ResponseDto>(message))
        } catch(e: HttpException) {
            emit(Resource.Error<ResponseDto>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<ResponseDto>("Couldn't reach server. Check your internet connection."))
        }
    }
}