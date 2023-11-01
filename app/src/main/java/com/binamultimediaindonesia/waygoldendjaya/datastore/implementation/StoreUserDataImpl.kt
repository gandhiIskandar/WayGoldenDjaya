package com.binamultimediaindonesia.waygoldendjaya.datastore.implementation

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.binamultimediaindonesia.waygoldendjaya.common.Constants.gson
import com.binamultimediaindonesia.waygoldendjaya.common.Constants.toJson
import com.binamultimediaindonesia.waygoldendjaya.common.Constants.toLoginDto
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.LoginDto
import com.binamultimediaindonesia.waygoldendjaya.datastore.abstraction.StoreUserDataRepository
import com.binamultimediaindonesia.waygoldendjaya.domain.model.Destination
import com.binamultimediaindonesia.waygoldendjaya.domain.model.User
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StoreUserDataImpl @Inject constructor(
    private val context:Context
    ) : StoreUserDataRepository {



    companion object {
        private val Context.dataStore : DataStore<Preferences> by preferencesDataStore("dataStoreToken")
        val LOGIN_DTO = stringPreferencesKey("loginDto")

    }

    override suspend fun putLoginDto(data: LoginDto) {
        context.dataStore.edit { preferences ->
            preferences[LOGIN_DTO] = data.toJson()
        }
    }
  override suspend fun clearData() {
       context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
    override suspend fun getLoginDto() =
        context.dataStore.data.map {
            preferences->
      val data =  preferences[LOGIN_DTO] ?: ""
            if(data!=""){
                //kalau data ada maka convert
                data.toLoginDto()
            }else{
                //kalau kosong maka kasih data default berisikan data null
                LoginDto()
            }

    }


}