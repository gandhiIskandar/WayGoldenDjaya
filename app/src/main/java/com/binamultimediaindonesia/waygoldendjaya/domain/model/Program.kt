package com.binamultimediaindonesia.waygoldendjaya.domain.model

data class Program(
    val program_name:String,
    val description:String,
    val sessions: List<Session>
)
