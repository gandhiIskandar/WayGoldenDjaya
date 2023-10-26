package com.binamultimediaindonesia.waygoldendjaya.representation.surah

import com.binamultimediaindonesia.waygoldendjaya.domain.model.alquran.Surah


data class SurahState (
    val isLoading:Boolean = false,
    val data: List<Surah>? = null,
    val error:String =""

)