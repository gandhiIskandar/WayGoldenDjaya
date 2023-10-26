package com.binamultimediaindonesia.waygoldendjaya.data.remote.dto

import com.binamultimediaindonesia.waygoldendjaya.domain.model.alquran.Surah

data class SurahDto (
    val code:Int,
    val message:String,
    val data:List<Surah>
        )



