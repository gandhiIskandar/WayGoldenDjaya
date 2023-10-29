package com.binamultimediaindonesia.waygoldendjaya.datastore.abstraction

import com.binamultimediaindonesia.waygoldendjaya.domain.model.Destination
import com.binamultimediaindonesia.waygoldendjaya.domain.model.User
import kotlinx.coroutines.flow.Flow

interface StoreUserDataRepository {

    suspend fun putToken(data:String)
    suspend fun getToken(): Flow<String?>
    suspend fun putUser(data: User)
    suspend fun getUser():Flow<User?>
    suspend fun putDestinations(data:List<Destination>)
    suspend fun getDestinations():Flow<List<Destination>>
    suspend fun clearDataStore()
    suspend fun putMuthawif(data:User)
    suspend fun getMuthawif():Flow<User?>

}