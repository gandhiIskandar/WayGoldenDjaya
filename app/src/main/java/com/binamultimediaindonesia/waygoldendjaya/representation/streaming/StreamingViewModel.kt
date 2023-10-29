package com.binamultimediaindonesia.waygoldendjaya.representation.streaming

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binamultimediaindonesia.waygoldendjaya.common.Resource
import com.binamultimediaindonesia.waygoldendjaya.datastore.abstraction.StoreUserDataRepository
import com.binamultimediaindonesia.waygoldendjaya.domain.model.User
import com.binamultimediaindonesia.waygoldendjaya.domain.use_case.get_streaming_data.GetStreamingDataUseCase
import com.binamultimediaindonesia.waygoldendjaya.representation.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StreamingViewModel @Inject constructor(
    private val getStreamingDataUseCase: GetStreamingDataUseCase,
    private val getUserDataUseCase: StoreUserDataRepository
): ViewModel() {


    private val _state = mutableStateOf(StreamingState())
    val state: State<StreamingState> = _state

   lateinit var userData : MutableState<User>

    init {
        viewModelScope.launch {

            try{
                userData = mutableStateOf(getUser())

                val token = getToken()

                val tokenVal = "Bearer $token"

                val header = mapOf<String,String>(
                    "Authorization" to tokenVal
                )

                getStreamingData(header, userData.value.group_id.toString())

            }catch (e: Exception){
                e.localizedMessage?.let { Log.d("error", it) }
            }


        }
    }

    private fun getStreamingData(headers : Map<String,String>, groupId:String) {
        getStreamingDataUseCase(headers,groupId).onEach { result->
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

    private suspend fun getToken():String{

        return getUserDataUseCase.getToken().first()!!

    }

    private suspend fun getUser(): User {
        return getUserDataUseCase.getUser().first()!!
    }


}