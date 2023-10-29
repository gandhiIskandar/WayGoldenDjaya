package com.binamultimediaindonesia.waygoldendjaya.data.remote.dto

import com.binamultimediaindonesia.waygoldendjaya.domain.model.Destination
import com.binamultimediaindonesia.waygoldendjaya.domain.model.User

data class LoginDto(
    val token: String ="",
    val message: String = "",
    val user: User?= null,
    val muthawif: User?=null,
    val destinations:List<Destination> = listOf(),
    val access: Boolean = false

    )

