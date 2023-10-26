package com.binamultimediaindonesia.waygoldendjaya.representation.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binamultimediaindonesia.waygoldendjaya.common.Resource
import com.binamultimediaindonesia.waygoldendjaya.datastore.abstraction.StoreUserDataRepository
import com.binamultimediaindonesia.waygoldendjaya.domain.use_case.get_home_screen_data.GetHomeScreenDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewmodel @Inject constructor(
    private val homeScreenDataUseCase: GetHomeScreenDataUseCase,
    private val storeUserData : StoreUserDataRepository
) : ViewModel() {
    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        viewModelScope.launch{

            Log.d("homeScreen", "vminit")

            val token ="Bearer " + getToken()

            val headers = mapOf(
                "Authorization" to token
            )
            getHomeScreenData(headers)

        }




    }


    private fun getHomeScreenData(headers : Map<String,String>) {
        homeScreenDataUseCase(headers).onEach { result->

            when(result){

                is Resource.Success -> {
                    _state.value = HomeState(homeScreenData = result.data)

                }

                is Resource.Error ->{

                    _state.value = HomeState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = HomeState(isLoading = true)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)

    }

    private suspend fun getToken():String{

    return storeUserData.getToken().first()!!


    }

}