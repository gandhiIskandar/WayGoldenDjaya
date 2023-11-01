package com.binamultimediaindonesia.waygoldendjaya.datastore.abstraction

import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.LoginDto
import com.binamultimediaindonesia.waygoldendjaya.domain.model.Destination
import com.binamultimediaindonesia.waygoldendjaya.domain.model.User
import kotlinx.coroutines.flow.Flow

interface StoreUserDataRepository {

    suspend fun putLoginDto(data:LoginDto)
    suspend fun getLoginDto(): Flow<LoginDto?>
    suspend fun clearData()


}