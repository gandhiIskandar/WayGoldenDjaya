package com.binamultimediaindonesia.waygoldendjaya.representation.schedule

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binamultimediaindonesia.waygoldendjaya.common.Resource
import com.binamultimediaindonesia.waygoldendjaya.common.Util.tokenGenerator
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.LoginDto
import com.binamultimediaindonesia.waygoldendjaya.datastore.abstraction.StoreUserDataRepository
import com.binamultimediaindonesia.waygoldendjaya.domain.use_case.get_schdules.GetSchedulesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val getSchedulesUseCase: GetSchedulesUseCase,
    private val userDataRepository: StoreUserDataRepository
): ViewModel() {

    private val _state = mutableStateOf(ScheduleState())

    val state: State<ScheduleState> = _state

    //untuk ambil data token saja
    private var dataDto = LoginDto()


    init {
        viewModelScope.launch {
            try {
                dataDto = getUserData()

                val token = dataDto.token
                val sessionId = dataDto.user?.session_id

                getSchedules(tokenGenerator(token),sessionId.toString())

            }catch (e: Exception){

                print(e.stackTrace)

            }


        }

    }

    private fun getSchedules(headers:Map<String,String>, idSession:String){

        getSchedulesUseCase.invoke(headers, idSession).onEach { result->

            when(result){
                is Resource.Loading -> {
                    _state.value = ScheduleState(isLoading = true)

                }
                is Resource.Success -> {
                    _state.value = ScheduleState(data = result.data!!)

                    Log.d("datanya", idSession)

                }
                is Resource.Error->{

                    _state.value = ScheduleState(error = result.message!!)
                }
            }

        }.launchIn(viewModelScope)

    }


    private suspend fun getUserData(): LoginDto {

        return userDataRepository.getLoginDto().first()!!

    }

}