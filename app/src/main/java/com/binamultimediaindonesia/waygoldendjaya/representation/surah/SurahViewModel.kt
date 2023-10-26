package com.binamultimediaindonesia.waygoldendjaya.representation.surah

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binamultimediaindonesia.waygoldendjaya.common.Resource
import com.binamultimediaindonesia.waygoldendjaya.domain.use_case.get_surah.GetSurahUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SurahViewModel @Inject constructor(
    private val getSurahUseCase: GetSurahUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SurahState())

    val state = _state

    init {

        getSurah()

    }

    private fun getSurah() {

        getSurahUseCase().onEach { result ->

            when(result){

                is Resource.Success -> {

                    _state.value = SurahState(data = result.data)

                }

                is Resource.Error -> {
                    _state.value = SurahState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = SurahState(isLoading = true)
                }
                else -> {}
            }

        }.launchIn(viewModelScope)


    }
}