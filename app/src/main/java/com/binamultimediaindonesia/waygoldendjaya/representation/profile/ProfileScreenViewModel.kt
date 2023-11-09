package com.binamultimediaindonesia.waygoldendjaya.representation.profile

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binamultimediaindonesia.waygoldendjaya.common.Resource
import com.binamultimediaindonesia.waygoldendjaya.common.Util.tokenGenerator
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.LoginDto
import com.binamultimediaindonesia.waygoldendjaya.datastore.abstraction.StoreUserDataRepository
import com.binamultimediaindonesia.waygoldendjaya.domain.use_case.get_user.GetUserUseCase
import com.binamultimediaindonesia.waygoldendjaya.domain.use_case.logout.LogoutUseCase
import com.binamultimediaindonesia.waygoldendjaya.domain.use_case.update_pin_user.UpdatePinUserUseCase
import com.binamultimediaindonesia.waygoldendjaya.domain.use_case.update_profile_image.UpdateProfileImageUseCase
import com.binamultimediaindonesia.waygoldendjaya.domain.use_case.update_user.UpdateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val userUseCase: GetUserUseCase,
    private val updateeUserUseCase: UpdateUserUseCase,
    private val storeUserDataRepository: StoreUserDataRepository,
    private val updateProfileImageUseCase: UpdateProfileImageUseCase,
    private val updatePinUserUseCase: UpdatePinUserUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {


    private val _updateState = mutableStateOf(UpdateState())
    val updateState: State<UpdateState> = _updateState

    private val _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

    private var dataPackage = LoginDto()

    init {
        viewModelScope.launch {
            try {
                dataPackage = getUserData()

                getUserApi(
                    dataPackage.user?.id.toString(),
                    tokenGenerator(dataPackage.token)

                )


            } catch (e: Exception) {
                e.localizedMessage?.let { it -> Log.d("Error", it) }


            }
        }

    }


    private fun getUserApi(id: String, headerMap: Map<String, String>) {

        userUseCase(id, headerMap).onEach { result ->

            when (result) {
                is Resource.Success -> {

                    _state.value = ProfileState(data = result.data)
                }
                is Resource.Error -> {
                    _state.value = ProfileState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = ProfileState(isLoading = true)
                }

            }


        }.launchIn(viewModelScope)
    }

     fun updateUserData(fieldMap: Map<String,String>) {
        updateeUserUseCase.invoke(dataPackage.user?.id.toString(), fieldMap, tokenGenerator(dataPackage.token)).onEach { result ->

            when (result) {
                is Resource.Success -> {
                    _updateState.value = UpdateState(message = result.data?.message?:"")
                    Log.d("testUpdate", result.data?.message?:"")
                }
                is Resource.Loading -> {
                    _updateState.value = UpdateState(isLoading = true)
                    Log.d("testUpdate", "loading")
                }
                else -> {
                    _updateState.value = UpdateState(message = result.message ?: "Unexpected Error")
                    Log.d("testUpdate", result.message?:"")
                }
            }


        }.launchIn(viewModelScope)

    }

    fun updatePinUser(data:String){

        updatePinUserUseCase(data, tokenGenerator(dataPackage.token)).onEach {  result->

            when(result){
                is Resource.Success -> {
                    _updateState.value = UpdateState(message = result.data?.message?:"")
                    Log.d("testPIN", result.data?.message?:"")

                }
                is Resource.Loading -> {
                    _updateState.value = UpdateState(isLoading = true)
                    Log.d("testPIN", result.message?:"loading")
                }
                is Resource.Error -> {
                    _updateState.value = UpdateState(message = result.message ?: "Unexpected Error")
                    Log.d("testPIN", result.message?:"error")
                }
            }

        }.launchIn(viewModelScope)



    }

    fun userLogout(logout:()->Unit){

        logoutUseCase( tokenGenerator(dataPackage.token)).onEach { result ->

            when(result){
                is Resource.Success -> {
                    storeUserDataRepository.clearData()
                   logout.invoke()

                }
                is Resource.Loading -> {
                    _updateState.value = UpdateState(isLoading = true)

                }
                is Resource.Error -> {
                    _updateState.value = UpdateState(message = result.message ?: "Unexpected Error")

                }
            }




        }.launchIn(viewModelScope)

    }


  fun updatePhotoProfile(data: MultipartBody.Part){

        updateProfileImageUseCase(data, tokenGenerator(dataPackage.token)).onEach { result ->




            when(result){
                is Resource.Success -> {
                    _updateState.value = UpdateState(message = result.data?.message?:"")
                    Log.d("testPhoto", result.data?.message?:"")

                }
                is Resource.Loading -> {
                    _updateState.value = UpdateState(isLoading = true)
                    Log.d("testPhoto", result.message?:"loading")
                }
                is Resource.Error -> {
                    _updateState.value = UpdateState(message = result.message ?: "Unexpected Error")
                    Log.d("testPhoto", result.message?:"error")
                }
            }

        }.launchIn(viewModelScope)

    }





    private suspend fun getUserData(): LoginDto {

        return storeUserDataRepository.getLoginDto().first()!!

    }

}