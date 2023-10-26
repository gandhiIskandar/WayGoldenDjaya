package com.binamultimediaindonesia.waygoldendjaya.domain.repository

import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.LoginDto
import com.binamultimediaindonesia.waygoldendjaya.domain.model.User

interface UserRepository {

    suspend fun getUserData(id:String, headers:Map<String,String>) : User

    suspend fun login(name:String, pin:String): LoginDto

}