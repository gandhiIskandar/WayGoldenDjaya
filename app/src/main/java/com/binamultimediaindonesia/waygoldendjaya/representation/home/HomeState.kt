package com.binamultimediaindonesia.waygoldendjaya.representation.home

import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.HomeScreenDto
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.LoginDto

data class HomeState (
    val isLoading:Boolean = false,
    val homeScreenData: HomeScreenDto? = null,
    val error:String =""

)
