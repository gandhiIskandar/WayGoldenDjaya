package com.binamultimediaindonesia.waygoldendjaya.domain.use_case.get_home_screen_data


import com.binamultimediaindonesia.waygoldendjaya.common.Resource
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.HomeScreenDto
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.LoginDto
import com.binamultimediaindonesia.waygoldendjaya.data.repository.HomeScreenDataRepositoryImpl
import com.binamultimediaindonesia.waygoldendjaya.datastore.abstraction.StoreUserDataRepository
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.HomeScreenDataRepository
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetHomeScreenDataUseCase @Inject constructor(
    private val repository: StoreUserDataRepository

) {

    operator fun invoke(headers : Map<String,String>) : Flow<Resource<HomeScreenDto>> = flow {
        try{
            emit(Resource.Loading<HomeScreenDto>())
           val user = repository.getUser().first()!!
            val destinations = repository.getDestinations().first()
            val homeScreenDto = HomeScreenDto(user = user, destinations = destinations)

            emit(Resource.Success<HomeScreenDto>(homeScreenDto))
        } catch(e: IOException) {
            emit(Resource.Error<HomeScreenDto>("Error Getting Data"))
        } catch (e :Exception){
            emit(Resource.Error<HomeScreenDto>(e.message?:"Unexpected Error"))
        }
    }

}