package com.binamultimediaindonesia.waygoldendjaya.datastore.implementation

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.binamultimediaindonesia.waygoldendjaya.common.Constants
import com.binamultimediaindonesia.waygoldendjaya.common.Constants.gson
import com.binamultimediaindonesia.waygoldendjaya.datastore.abstraction.StoreUserDataRepository
import com.binamultimediaindonesia.waygoldendjaya.domain.model.Destination
import com.binamultimediaindonesia.waygoldendjaya.domain.model.User
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StoreUserDataImpl @Inject constructor(
    private val context:Context
    ) : StoreUserDataRepository {


    private fun List<Destination>.toJson():String{
        return gson.toJson(this)
    }

    private fun String.toDestinationList():List<Destination>{

        return  gson.fromJson(this, object : TypeToken<List<Destination>>() {}.type )
    }

    private fun User.toJson():String{
        return gson.toJson(this)

    }

    private fun String.toUser():User{
        return gson.fromJson(this, User::class.java)
    }

    companion object {
        private val Context.dataStore : DataStore<Preferences> by preferencesDataStore("dataStoreToken")
        val TOKEN = stringPreferencesKey("token")
        val USER = stringPreferencesKey("user")
        val DESTINATIONS = stringPreferencesKey("destinations")
    }

    override suspend fun putToken(data: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN] = data
        }
    }
   override suspend fun clearDataStore() {
       context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
    override suspend fun getToken()=
        context.dataStore.data.map {
            preferences->
        preferences[TOKEN] ?: ""
    }


    override suspend fun putUser(data: User) {

        context.dataStore.edit { preferences ->
            preferences[USER] = data.toJson()
        }
    }

    override suspend fun getUser()= context.dataStore.data.map{
            preferences ->
        val userJson = preferences[USER] ?: ""
        userJson.toUser()

    }

    override suspend fun putDestinations(data: List<Destination>) {
        context.dataStore.edit { preferences ->

            preferences[DESTINATIONS] = data.toJson()

        }
    }

    override suspend fun getDestinations() = context.dataStore.data.map {  preferences ->
        val destinationsJson = preferences[DESTINATIONS]?:""
        destinationsJson.toDestinationList()
    }
}