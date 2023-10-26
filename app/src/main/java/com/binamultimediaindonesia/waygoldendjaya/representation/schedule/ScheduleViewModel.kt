package com.binamultimediaindonesia.waygoldendjaya.representation.schedule

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binamultimediaindonesia.waygoldendjaya.common.Resource
import com.binamultimediaindonesia.waygoldendjaya.domain.use_case.get_schdule.GetScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    val getScheduleUseCase: GetScheduleUseCase
): ViewModel() {

    private val _state = mutableStateOf(ScheduleState())

    val state: State<ScheduleState> = _state

     fun getSchedules(headers:Map<String,String>, idSession:String){

        getScheduleUseCase.invoke(headers, idSession).onEach { result->

            when(result){
                is Resource.Loading -> {
                    _state.value = ScheduleState(isLoading = true)

                }
                is Resource.Success -> {
                    _state.value = ScheduleState(data = result.data!!)

                }
                is Resource.Error->{

                    _state.value = ScheduleState(error = result.message!!)
                }
            }

        }.launchIn(viewModelScope)

    }

}