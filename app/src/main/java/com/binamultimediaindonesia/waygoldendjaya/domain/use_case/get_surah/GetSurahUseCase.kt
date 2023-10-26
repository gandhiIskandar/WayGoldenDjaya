package com.binamultimediaindonesia.waygoldendjaya.domain.use_case.get_surah

import com.binamultimediaindonesia.waygoldendjaya.common.Resource
import com.binamultimediaindonesia.waygoldendjaya.domain.model.alquran.Surah
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.GetSurahRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSurahUseCase @Inject constructor(
    private val repository: GetSurahRepository
){

    operator fun invoke() : Flow<Resource<List<Surah>>> = flow {
        try{
            emit(Resource.Loading<List<Surah>>())
            val surah = repository.getSurah().data
            emit(Resource.Success<List<Surah>>(surah))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Surah>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<Surah>>("Couldn't reach server. Check your internet connection."))
        }
    }
}
