package com.binamultimediaindonesia.waygoldendjaya.domain.use_case.login

import com.binamultimediaindonesia.waygoldendjaya.common.Resource
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.LoginDto
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: UserRepository
) {

    operator fun invoke(name:String, pin:String) : Flow<Resource<LoginDto>> = flow {


        try{
            emit(Resource.Loading<LoginDto>())
            val loginData = repository.login(name, pin)



            emit(Resource.Success<LoginDto>(loginData))
        } catch(e: HttpException) {
            emit(Resource.Error<LoginDto>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<LoginDto>("Couldn't reach server. Check your internet connection."))
        }
    }

}