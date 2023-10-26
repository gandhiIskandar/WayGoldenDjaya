package com.binamultimediaindonesia.waygoldendjaya.data.remote.dto

import com.binamultimediaindonesia.waygoldendjaya.domain.model.Destination
import com.binamultimediaindonesia.waygoldendjaya.domain.model.User

data class HomeScreenDto (
    val destinations : List<Destination> = listOf(),
    val user : User? =null
        )