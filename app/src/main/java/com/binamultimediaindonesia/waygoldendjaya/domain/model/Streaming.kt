package com.binamultimediaindonesia.waygoldendjaya.domain.model

data class Streaming(
    val group: Group,
    val url:String,
    val status:Boolean,
    val channel:Channel
)
