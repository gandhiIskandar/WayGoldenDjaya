package com.binamultimediaindonesia.waygoldendjaya.representation.destination_detail

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binamultimediaindonesia.waygoldendjaya.common.Constants.PARAM_ID_DESTINATION
import com.binamultimediaindonesia.waygoldendjaya.common.Resource
import com.binamultimediaindonesia.waygoldendjaya.common.Util.tokenGenerator
import com.binamultimediaindonesia.waygoldendjaya.datastore.abstraction.StoreUserDataRepository
import com.binamultimediaindonesia.waygoldendjaya.domain.use_case.get_destination.GetDestinationUseCase
import com.binamultimediaindonesia.waygoldendjaya.representation.ayah.AyahState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DestinationViewModel @Inject constructor(
    val getDestinationUseCase: GetDestinationUseCase,
    private val dataRepository: StoreUserDataRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(DestinationState())

    val state = _state


    init {
        viewModelScope.launch {

            try {

                savedStateHandle.get<String>(PARAM_ID_DESTINATION)?.let{

                    val token = tokenGenerator(dataRepository.getLoginDto().first()!!.token)

                    getDestination(token, it)

                }


            }catch (e: Exception){

                e.localizedMessage?.let { Log.e("destinationError", it) }

            }
        }
    }

    private fun getDestination(header:Map<String,String>, id:String){

        getDestinationUseCase(header, id).onEach { result ->
            when(result){
                is Resource.Success ->{
                    _state.value = DestinationState(destination = result.data)


                }
                is Resource.Error ->{
                    _state.value = DestinationState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = DestinationState(isLoading = true)
                }
                else -> {}

            }


        }.launchIn(viewModelScope)

    }




}