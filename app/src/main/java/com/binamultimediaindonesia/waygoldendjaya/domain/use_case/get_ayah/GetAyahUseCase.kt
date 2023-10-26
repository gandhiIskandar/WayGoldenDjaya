package com.binamultimediaindonesia.waygoldendjaya.domain.use_case.get_ayah



import com.binamultimediaindonesia.waygoldendjaya.common.Resource
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.AyahDto
import com.binamultimediaindonesia.waygoldendjaya.domain.model.alquran.Ayah
import com.binamultimediaindonesia.waygoldendjaya.domain.model.alquran.Surah
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.GetAyahRepository
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.GetSurahRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAyahUseCase @Inject constructor(
    private val repository: GetAyahRepository
){

    operator fun invoke(id:String) : Flow<Resource<AyahDto>> = flow {
        try{
            emit(Resource.Loading<AyahDto>())
            val ayahDto = repository.getAyah(id)
            emit(Resource.Success<AyahDto>(ayahDto))
        } catch(e: HttpException) {
            emit(Resource.Error<AyahDto>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<AyahDto>("Couldn't reach server. Check your internet connection."))
        }
    }
}
