package com.binamultimediaindonesia.waygoldendjaya

import androidx.lifecycle.ViewModel
import com.binamultimediaindonesia.waygoldendjaya.datastore.abstraction.StoreUserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val dataUserDataRepository: StoreUserDataRepository
) : ViewModel() {





}