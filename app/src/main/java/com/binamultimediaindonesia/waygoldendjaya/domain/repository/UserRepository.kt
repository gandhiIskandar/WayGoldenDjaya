package com.binamultimediaindonesia.waygoldendjaya.domain.repository

import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.LoginDto
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.ResponseDto
import com.binamultimediaindonesia.waygoldendjaya.domain.model.User
import okhttp3.MultipartBody
import retrofit2.Response

interface UserRepository {

    suspend fun getUserData(id:String, headers:Map<String,String>) : User

    suspend fun login(name:String, pin:String): LoginDto

    suspend fun updateUser(id:String, headerField :Map<String, String>, headers:Map<String,String>) :ResponseDto

    suspend fun updatePhotoProfile(data: MultipartBody.Part, headers: Map<String, String>): ResponseDto

    suspend fun updatePin(data:String, headers: Map<String, String>):ResponseDto

    suspend fun logout(headers: Map<String, String>):ResponseDto

    suspend fun getHcd(headers: Map<String, String>):LoginDto



}