package com.binamultimediaindonesia.waygoldendjaya.domain.model.alquran

data class Surah (
        val nomor:String,
        val nama : String,
        val namaLatin:String,
        val jumlahAyat: Int,
        val tempatTurun:String,
        val arti:String,
        val deskripsi:String,
        val audioFull:Map<String,String>,
        val ayat:List<Ayah> = listOf()

        )