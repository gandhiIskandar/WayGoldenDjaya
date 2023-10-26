package com.binamultimediaindonesia.waygoldendjaya.domain.model.alquran

data class Ayah (
    val nomorAyat: Int,
    val teksArab: String,
    val teksLatin: String,
    val teksIndonesia:String,
    val audio:Map<String,String>
        )