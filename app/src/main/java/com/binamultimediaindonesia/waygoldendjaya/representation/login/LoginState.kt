package com.binamultimediaindonesia.waygoldendjaya.representation.login

import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.LoginDto

data class LoginState(
    val isLoading:Boolean = false,
    val loginData:LoginDto? = null,
    val error:String =""
)