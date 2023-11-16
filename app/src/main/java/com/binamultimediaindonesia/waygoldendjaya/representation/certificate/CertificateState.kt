package com.binamultimediaindonesia.waygoldendjaya.representation.certificate

import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.AyahDto
import com.binamultimediaindonesia.waygoldendjaya.domain.model.Certificate

data class CertificateState (
    val isLoading:Boolean = false,
    val data: Certificate? = null,
    val error:String =""
)