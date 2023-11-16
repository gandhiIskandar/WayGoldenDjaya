package com.binamultimediaindonesia.waygoldendjaya.domain.model

import com.binamultimediaindonesia.waygoldendjaya.domain.model.User

data class Certificate (
    val kode:String,
    val user: User,
    val url:String,
    val session:Session
        )