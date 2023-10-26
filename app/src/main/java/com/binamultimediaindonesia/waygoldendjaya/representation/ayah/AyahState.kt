package com.binamultimediaindonesia.waygoldendjaya.representation.ayah

import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.AyahDto
import com.binamultimediaindonesia.waygoldendjaya.domain.model.alquran.Ayah


data class AyahState (
    val isLoading:Boolean = false,
    val data: AyahDto? = null,
    val error:String =""
        )