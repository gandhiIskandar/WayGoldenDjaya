package com.binamultimediaindonesia.waygoldendjaya.representation.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.binamultimediaindonesia.waygoldendjaya.common.Constants
import com.binamultimediaindonesia.waygoldendjaya.common.Resource
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.LoginDto
import com.binamultimediaindonesia.waygoldendjaya.datastore.abstraction.StoreUserDataRepository
import com.binamultimediaindonesia.waygoldendjaya.domain.use_case.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val storeUserData: StoreUserDataRepository

) : ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

init {
    checkLoginorNot()
}


    fun proccedLogin(name: String, pin: String) {
        loginUseCase(name, pin).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    if(result.data!!.access){

                        storeUserData(result.data)

                    }
                    _state.value = LoginState(loginData = result.data)
                }
                is Resource.Error -> {
                    _state.value = LoginState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = LoginState(isLoading = true)
                }

                else -> {}
            }


        }.launchIn(viewModelScope)
    }

    private suspend fun storeUserData(data: LoginDto) {

        storeUserData.clearDataStore()
        storeUserData.putDestinations(data.destinations)
        storeUserData.putToken(data.token)
        storeUserData.putUser(data.user!!)
        data.muthawif?.let {
            storeUserData.putMuthawif(it)
        }


    }

   fun checkLoginorNot(){
     viewModelScope.launch {
         val token = storeUserData.getToken().first()

         if(token!= ""){
             _state.value = LoginState(loginData = LoginDto(access=true))
         }

           }
       }

    override fun onCleared() {
        super.onCleared()
    }
}






