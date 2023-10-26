package com.binamultimediaindonesia.waygoldendjaya.data.repository

import com.binamultimediaindonesia.waygoldendjaya.data.remote.WayGoldenDjayaApi
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.LoginDto
import com.binamultimediaindonesia.waygoldendjaya.domain.model.User
import com.binamultimediaindonesia.waygoldendjaya.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api : WayGoldenDjayaApi
) : UserRepository {

    override suspend fun getUserData(id:String, headers: Map<String,String>): User {
      return api.getUserData(id, headers)
    }

    override suspend fun login(name: String, pin: String): LoginDto {
      return  api.login(name, pin)
    }
}