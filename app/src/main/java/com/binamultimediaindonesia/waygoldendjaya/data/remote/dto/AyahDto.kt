package com.binamultimediaindonesia.waygoldendjaya.data.remote.dto

import com.binamultimediaindonesia.waygoldendjaya.domain.model.alquran.Ayah
import com.binamultimediaindonesia.waygoldendjaya.domain.model.alquran.Surah

data class AyahDto (
    val code:Int,
    val message:String,
    val data: Surah,
        )


