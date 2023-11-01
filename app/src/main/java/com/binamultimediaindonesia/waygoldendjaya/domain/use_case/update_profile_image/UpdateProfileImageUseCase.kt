package com.binamultimediaindonesia.waygoldendjaya.domain.use_case.update_profile_image

import com.binamultimediaindonesia.waygoldendjaya.common.Resource
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.ResponseDto
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class UpdateProfileImageUseCase @Inject constructor(
    private val repository: UserRepository
){

    operator fun invoke(data: MultipartBody.Part, headerMap: Map<String,String>) : Flow<Resource<ResponseDto>> = flow {
        try{
            emit(Resource.Loading<ResponseDto>())
            val message = repository.updatePhotoProfile(data, headerMap)
            emit(Resource.Success<ResponseDto>(message))
        } catch(e: HttpException) {
            emit(Resource.Error<ResponseDto>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<ResponseDto>(e.localizedMessage?: "Couldn't reach server. Check your internet connection."))
        }
    }
}