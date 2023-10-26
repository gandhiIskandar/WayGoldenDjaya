package com.binamultimediaindonesia.waygoldendjaya.representation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.binamultimediaindonesia.waygoldendjaya.domain.player.StreamingStateViewModel
import com.binamultimediaindonesia.waygoldendjaya.domain.player.callback.StreamingCallback
import com.binamultimediaindonesia.waygoldendjaya.representation.ayah.AyahScreen
import com.binamultimediaindonesia.waygoldendjaya.representation.home.HomeScreen
import com.binamultimediaindonesia.waygoldendjaya.representation.login.LoginScreen
import com.binamultimediaindonesia.waygoldendjaya.representation.streaming.StreamingScreen
import com.binamultimediaindonesia.waygoldendjaya.representation.surah.SurahScreen

@UnstableApi
@Composable
fun Navigation(navController: NavHostController, streamingStateViewModel: StreamingStateViewModel, streamingCallback: StreamingCallback) {



    NavHost(navController = navController, startDestination = "login")
    {



        composable("home"){
            HomeScreen(navController = navController)

        }
        composable("login"){

            LoginScreen(navController)

        }
        composable("streaming"){

           StreamingScreen(streamingStateViewModel = streamingStateViewModel, streamingCallback = streamingCallback)

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

    }
}