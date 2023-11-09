package com.binamultimediaindonesia.waygoldendjaya.domain.use_case.logout


import com.binamultimediaindonesia.waygoldendjaya.common.Resource
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.ResponseDto
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: UserRepository
){

    operator fun invoke( header:Map<String,String>) : Flow<Resource<ResponseDto>> = flow {
        try{
            emit(Resource.Loading<ResponseDto>())
            val message = repository.logout(header)
            emit(Resource.Success<ResponseDto>(message))
        } catch(e: HttpException) {
            emit(Resource.Error<ResponseDto>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<ResponseDto>(e.localizedMessage?: "Couldn't reach server. Check your internet connection."))
        }
    }
}