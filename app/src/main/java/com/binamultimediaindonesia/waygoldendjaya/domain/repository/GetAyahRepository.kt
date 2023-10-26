package com.binamultimediaindonesia.waygoldendjaya.domain.repository

import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.AyahDto

interface GetAyahRepository {

suspend fun getAyah(nomor:String):AyahDto

}