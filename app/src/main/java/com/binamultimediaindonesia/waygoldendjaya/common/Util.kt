package com.binamultimediaindonesia.waygoldendjaya.common

import android.content.Context
import android.widget.Toast
import com.binamultimediaindonesia.waygoldendjaya.datastore.abstraction.StoreUserDataRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

object Util {

    fun showDateInIndonesia(date: Date): String {

        val formattedDate = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("Id"))

        return formattedDate.format(date)

    }

    fun showHourInIndonesia(date: Date): String{

        val formattedDate = SimpleDateFormat("HH:mm", Locale("Id"))

        return formattedDate.format(date)

    }

    fun convertToDate(data:String): Date {
        val formatTanggal = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale("id"))
        return formatTanggal.parse(data)!!
    }

    fun toastShort(id:Int, context:Context){

        Toast.makeText(context, id, Toast.LENGTH_SHORT).show()

    }

    fun toastLong(id:Int, context:Context){
        Toast.makeText(context, id, Toast.LENGTH_LONG).show()
    }


    fun tokenGenerator(token:String):Map<String,String>{
        return mapOf(
            "Authorization" to "Bearer $token"
        )
    }






}