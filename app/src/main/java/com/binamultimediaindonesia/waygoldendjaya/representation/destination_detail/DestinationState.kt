package com.binamultimediaindonesia.waygoldendjaya.representation.destination_detail

import com.binamultimediaindonesia.waygoldendjaya.domain.model.Destination

data class DestinationState (
    val isLoading:Boolean = false,
    val destination: Destination? = null,
    val error:String =""
)