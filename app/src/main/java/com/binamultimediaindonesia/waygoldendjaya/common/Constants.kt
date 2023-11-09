package com.binamultimediaindonesia.waygoldendjaya.common


import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.binamultimediaindonesia.waygoldendjaya.domain.model.menu_model.MenuModel
import com.binamultimediaindonesia.waygoldendjaya.R
import com.binamultimediaindonesia.waygoldendjaya.common.Constants.toJson
import com.binamultimediaindonesia.waygoldendjaya.common.Constants.toLoginDto
import com.binamultimediaindonesia.waygoldendjaya.common.Util.convertToDate
import com.binamultimediaindonesia.waygoldendjaya.data.remote.dto.LoginDto
import com.binamultimediaindonesia.waygoldendjaya.domain.model.Destination
import com.binamultimediaindonesia.waygoldendjaya.domain.model.Schedule
import com.binamultimediaindonesia.waygoldendjaya.domain.model.User
import com.binamultimediaindonesia.waygoldendjaya.domain.model.menu_model.BottomNavItem
import com.google.gson.Gson

object Constants {


    const val PARAM_ID_DESTINATION = "destination"
    const val SURAH_NUMBER = "nomor"
    val MENU_LIST = listOf(
        MenuModel(
            icon = R.drawable.ic__alquran,
            route = "alquran",
            text = R.string.alquran,
            isFinished = true

        ),
        MenuModel(
            icon = R.drawable.ic__hotelku,
            route = "hotel",
            text = R.string.hotel

        ),
        MenuModel(
            icon = R.drawable.ic__kiblat,
            route = "kiblat",
            text = R.string.kiblat

        ),
        MenuModel(
            icon = R.drawable.ic__panduan,
            route = "panduan",
            text = R.string.panduan,
            isFinished = true


        ),
        MenuModel(
            icon = R.drawable.ic__jadwal,
            route = "schedules",
            text = R.string.jadwal,
            isFinished = true

        ),
        MenuModel(
            icon = R.drawable.ic__livegroup,
            route = "streaming",
            text = R.string.live_group,
            isFinished = true

        ),
        MenuModel(
            icon = R.drawable.ic__liveumum,
            route = "streaming_umum",
            text = R.string.live_umum

        ),
        MenuModel(
            icon = R.drawable.ic__gallery,
            route = "gallery",
            text = R.string.gallery

        ),
    )
    val BOTTOM_MENU_LIST = listOf(
        BottomNavItem(name = "Home", route = "home", icon = Icons.Default.Home),
        BottomNavItem(name = "Jadwal", route = "schedules", icon = Icons.Default.DateRange  ),
        BottomNavItem(name = "Profile", route = "profile", icon = Icons.Default.Person)
    )
    val gson = Gson()

    const val APP_ID: Long = 912748247
    const val APP_SIGN = "3d05a5ed29fb09d683e2c8141b665e57d1dc374fd9dd6d1a98b6a354897830c8"


    fun LoginDto.toJson(): String {
        return gson.toJson(this)
    }

    fun User.toJson():String{
        return gson.toJson(this)
    }

    fun String.toLoginDto(): LoginDto {
        return gson.fromJson(this, LoginDto::class.java)
    }

    fun String.toUser():User{
        return gson.fromJson(this, User::class.java)
    }


}