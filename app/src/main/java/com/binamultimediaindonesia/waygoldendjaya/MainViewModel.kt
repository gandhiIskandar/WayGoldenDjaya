package com.binamultimediaindonesia.waygoldendjaya

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.LoginDto
import com.binamultimediaindonesia.waygoldendjaya.datastore.abstraction.StoreUserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataUserDataRepository: StoreUserDataRepository
) : ViewModel() {





}