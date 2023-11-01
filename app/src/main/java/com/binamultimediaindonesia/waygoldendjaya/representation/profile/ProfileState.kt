package com.binamultimediaindonesia.waygoldendjaya.representation.profile


import com.binamultimediaindonesia.waygoldendjaya.domain.model.User

data class ProfileState (

    val isLoading:Boolean = false,
    val data: User? = null,
    val error:String =""

)