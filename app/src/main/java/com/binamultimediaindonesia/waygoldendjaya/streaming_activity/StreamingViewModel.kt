package com.binamultimediaindonesia.waygoldendjaya.streaming_activity

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binamultimediaindonesia.waygoldendjaya.common.Resource
import com.binamultimediaindonesia.waygoldendjaya.common.Util.tokenGenerator
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.LoginDto
import com.binamultimediaindonesia.waygoldendjaya.datastore.abstraction.StoreUserDataRepository
import com.binamultimediaindonesia.waygoldendjaya.domain.model.User
import com.binamultimediaindonesia.waygoldendjaya.domain.use_case.send_notification.SendNotificationUseCase
import com.binamultimediaindonesia.waygoldendjaya.representation.ayah.AyahState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StreamingViewModel @Inject constructor(
    private val userDataRepository: StoreUserDataRepository,
    private val sendNotificationUseCase: SendNotificationUseCase
) : ViewModel() {

    private val _state = mutableStateOf("")

    val state = _state

   private var userData: User? = null
  private var muthawiff: User? = null



    fun performInit(callback:(List<User>)->Unit){
        viewModelScope.launch {
            val rawData = getData()

            rawData?.let {

                userData = rawData.user
                muthawiff = rawData.muthawif

                callback.invoke(
                    listOf(userData!!, muthawiff!!)
                )

                if(userData!!.is_leader){
                    sendNotification(tokenGenerator(it.token), userData!!.group.group_name)
                }


            }

        }

    }

    private suspend fun getData(): LoginDto? {
        return userDataRepository.getLoginDto().first()
    }

    private fun sendNotification (header:Map<String,String>, groupName:String){
        sendNotificationUseCase(groupName, header).onEach { result->


            when(result){
                is Resource.Success ->{



                    _state.value = result.data?.message ?: "Success"


                }
                is Resource.Error ->{
                    _state.value = result.message ?: "An unexpected error occured"

                }

                is Resource.Loading -> {

                }
                else -> {}

            }

        }.launchIn(viewModelScope)
    }

}