package com.binamultimediaindonesia.waygoldendjaya.domain.use_case.get_certificate

import com.binamultimediaindonesia.waygoldendjaya.common.Resource
import com.binamultimediaindonesia.waygoldendjaya.domain.model.Certificate
import com.binamultimediaindonesia.waygoldendjaya.domain.model.Destination
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.GetDestinationRepository
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject


class GetCertificateUseCase @Inject constructor(
    val repository: UserRepository
){

    operator fun invoke(header:Map<String,String>): Flow<Resource<Certificate>> = flow {
        try {
            emit(Resource.Loading<Certificate>())
            val data = repository.getCertificate(header)
            emit(Resource.Success<Certificate>(data))
        } catch (e: IOException) {
            emit(Resource.Error<Certificate>("Error Getting Data"))
        } catch (e: Exception) {
            emit(Resource.Error<Certificate>(e.message ?: "Unexpected Error"))
        }
    }


}