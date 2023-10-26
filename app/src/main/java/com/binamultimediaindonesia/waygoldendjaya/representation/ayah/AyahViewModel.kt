package com.binamultimediaindonesia.waygoldendjaya.representation.ayah

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binamultimediaindonesia.waygoldendjaya.common.Constants
import com.binamultimediaindonesia.waygoldendjaya.common.Resource
import com.binamultimediaindonesia.waygoldendjaya.domain.use_case.get_ayah.GetAyahUseCase
import com.binamultimediaindonesia.waygoldendjaya.representation.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AyahViewModel @Inject constructor(
    val getAyahUseCase: GetAyahUseCase,
    savedStateHandle: SavedStateHandle
):ViewModel() {

    private val _state = mutableStateOf(AyahState())

    val state = _state

    init{
        savedStateHandle.get<String>(Constants.SURAH_NUMBER)?.let { surahNumber ->

            Log.d("surahNumber", surahNumber)

            getAyah(surahNumber)
        }
    }

    private fun getAyah(surahNumber:String){
        getAyahUseCase(surahNumber).onEach { result->

            Log.d("ayah",result.message?:"Tidak ada kesalahan")
            Log.d("ayah",result.data.toString())

            when(result){
                 is Resource.Success ->{
                     _state.value = AyahState(data = result.data)


                 }
                is Resource.Error ->{
                    _state.value = AyahState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = AyahState(isLoading = true)
                }
                else -> {}

            }

        }.launchIn(viewModelScope)
    }



}