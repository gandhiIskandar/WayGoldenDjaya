package com.binamultimediaindonesia.waygoldendjaya.representation.certificate

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binamultimediaindonesia.waygoldendjaya.common.Resource
import com.binamultimediaindonesia.waygoldendjaya.common.Util.tokenGenerator
import com.binamultimediaindonesia.waygoldendjaya.datastore.abstraction.StoreUserDataRepository
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.UserRepository
import com.binamultimediaindonesia.waygoldendjaya.domain.use_case.get_certificate.GetCertificateUseCase
import com.binamultimediaindonesia.waygoldendjaya.representation.ayah.AyahState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CertificateViewModel @Inject constructor(
    private val useCase: GetCertificateUseCase,
    private val userDataRepository: StoreUserDataRepository
) : ViewModel() {

    private val _state = mutableStateOf(CertificateState())

    val state = _state

    private val _tokenState = mutableStateOf("")

    val tokenState = _tokenState


    init {
        viewModelScope.launch {
            try {

                val token = getToken()?.let {
                    _tokenState.value = it
                    tokenGenerator(it)
                }

                if (token != null) {
                    getCertificate(token)
                }


            } catch (e: Exception) {

            }
        }


    }


    private fun getCertificate(headers: Map<String, String>) {
        useCase.invoke(headers).onEach { result ->

            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        _state.value = CertificateState(data = it)
                    }

                }

                is Resource.Loading -> {
                    _state.value = CertificateState(true)
                }

                else -> {
                    _state.value = CertificateState(error = result.message ?: "Unexpected Error")
                }
            }


        }.launchIn(viewModelScope)
    }



    private suspend fun getToken(): String? {

        return userDataRepository.getLoginDto().first()?.token

    }

}