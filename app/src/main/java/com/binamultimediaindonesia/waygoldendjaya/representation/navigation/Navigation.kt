package com.binamultimediaindonesia.waygoldendjaya.representation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.binamultimediaindonesia.waygoldendjaya.representation.ayah.AyahScreen
import com.binamultimediaindonesia.waygoldendjaya.representation.home.HomeScreen
import com.binamultimediaindonesia.waygoldendjaya.representation.login.LoginScreen
import com.binamultimediaindonesia.waygoldendjaya.representation.streaming.StreamingScreen
import com.binamultimediaindonesia.waygoldendjaya.representation.surah.SurahScreen


@Composable
fun Navigation(navController: NavHostController) {



    NavHost(navController = navController, startDestination = "login")
    {



        composable("home"){
            HomeScreen(navController = navController)

        }
        composable("login"){

            LoginScreen(navController)

        }
        composable("streaming"){

      StreamingScreen()

        }
        composable("alquran"){
            SurahScreen(navController = navController, modifier = Modifier )
        }
        composable("surah/{nomor}"){
            AyahScreen(modifier = Modifier)
        }
        composable("profile"){

        }
        composable("notification"){

        }
        composable("gallery"){

        }
        composable("streaming_umum"){

        }

    }
}