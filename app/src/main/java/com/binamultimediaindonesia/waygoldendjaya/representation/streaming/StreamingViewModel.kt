package com.binamultimediaindonesia.waygoldendjaya.representation.streaming

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binamultimediaindonesia.waygoldendjaya.common.Resource
import com.binamultimediaindonesia.waygoldendjaya.domain.use_case.get_streaming_data.GetStreamingDataUseCase
import com.binamultimediaindonesia.waygoldendjaya.representation.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StreamingViewModel @Inject constructor(
    private val getStreamingDataUseCase: GetStreamingDataUseCase
): ViewModel() {


    private val _state = mutableStateOf(StreamingState())
    val state: State<StreamingState> = _state

    private fun getStreamingData(headers : Map<String,String>) {
        getStreamingDataUseCase(headers).onEach { result->
            when(result){
                is Resource.Success -> {
                    _state.value = StreamingState(data = result.data)


                }

                is Resource.Error ->{
                    _state.value = StreamingState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = StreamingState(isLoading = true)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)

    }


}