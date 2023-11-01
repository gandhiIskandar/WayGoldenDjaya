package com.binamultimediaindonesia.waygoldendjaya.representation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.binamultimediaindonesia.waygoldendjaya.StartStreaming
import com.binamultimediaindonesia.waygoldendjaya.representation.ayah.AyahScreen
import com.binamultimediaindonesia.waygoldendjaya.representation.home.HomeScreen
import com.binamultimediaindonesia.waygoldendjaya.representation.login.LoginScreen
import com.binamultimediaindonesia.waygoldendjaya.representation.profile.ProfileScreen
import com.binamultimediaindonesia.waygoldendjaya.representation.streaming.StreamingScreen
import com.binamultimediaindonesia.waygoldendjaya.representation.surah.SurahScreen


@Composable
fun Navigation(navController: NavHostController, streaming: StartStreaming) {



    NavHost(navController = navController, startDestination = "login")
    {



        composable("home"){
            HomeScreen(navController = navController, streaming = streaming)

        }
        composable("login"){

            LoginScreen(navController)

        }

        composable("alquran"){
            SurahScreen(navController = navController, modifier = Modifier )
        }
        composable("surah/{nomor}"){
            AyahScreen(modifier = Modifier)
        }
        composable("profile"){
            ProfileScreen()

        }
        composable("notification"){

        }
        composable("gallery"){

        }
        composable("streaming_umum"){

        }

    }
}