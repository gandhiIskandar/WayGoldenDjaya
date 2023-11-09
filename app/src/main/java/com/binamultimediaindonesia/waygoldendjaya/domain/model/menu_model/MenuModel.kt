package com.binamultimediaindonesia.waygoldendjaya.domain.model.menu_model

import android.graphics.drawable.Drawable

data class MenuModel (
    val icon : Int,
    val route : String,
    val text : Int,
    var isFinished:Boolean = false
        )