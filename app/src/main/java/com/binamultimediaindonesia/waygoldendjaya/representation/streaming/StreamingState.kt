package com.binamultimediaindonesia.waygoldendjaya.representation.streaming

import com.binamultimediaindonesia.waygoldendjaya.domain.model.Streaming

data class StreamingState (
    val isLoading:Boolean = false,
    val data: Streaming? = null,
    val error:String =""

)