package com.binamultimediaindonesia.waygoldendjaya.domain.use_case.get_user

import com.binamultimediaindonesia.waygoldendjaya.common.Resource
import com.binamultimediaindonesia.waygoldendjaya.domain.model.User
import com.binamultimediaindonesia.waygoldendjaya.domain.model.alquran.Surah
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.GetSurahRepository
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

import java.io.IOException
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
){

    operator fun invoke(id:String, headerMap: Map<String,String> ) : Flow<Resource<User>> = flow {
        try{
            emit(Resource.Loading<User>())
            val user = repository.getUserData(id, headerMap)
            emit(Resource.Success<User>(user))
        } catch(e: HttpException) {
            emit(Resource.Error<User>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<User>("Couldn't reach server. Check your internet connection."))
        }
    }
}