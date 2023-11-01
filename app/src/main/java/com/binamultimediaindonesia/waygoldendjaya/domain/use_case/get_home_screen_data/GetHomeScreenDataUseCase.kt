package com.binamultimediaindonesia.waygoldendjaya.domain.use_case.get_home_screen_data


import androidx.lifecycle.SavedStateHandle
import com.binamultimediaindonesia.waygoldendjaya.common.Constants
import com.binamultimediaindonesia.waygoldendjaya.common.Constants.toLoginDto
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
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject

class GetHomeScreenDataUseCase @Inject constructor(
   private val storeUserDataRepository: StoreUserDataRepository

) {

    operator fun invoke(): Flow<Resource<LoginDto>> = flow {
        try {
            emit(Resource.Loading<LoginDto>())
            val loginDto = storeUserDataRepository.getLoginDto().first()
            emit(Resource.Success<LoginDto>(loginDto!!))
        } catch (e: IOException) {
            emit(Resource.Error<LoginDto>("Error Getting Data"))
        } catch (e: Exception) {
            emit(Resource.Error<LoginDto>(e.message ?: "Unexpected Error"))
        }
    }
}

