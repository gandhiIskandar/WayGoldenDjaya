package com.binamultimediaindonesia.waygoldendjaya


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.binamultimediaindonesia.waygoldendjaya.streaming_activity.StreamingActivity
import com.binamultimediaindonesia.waygoldendjaya.common.Constants.BOTTOM_MENU_LIST
import com.binamultimediaindonesia.waygoldendjaya.datastore.abstraction.StoreUserDataRepository
import com.binamultimediaindonesia.waygoldendjaya.download.AndroidDownloader
import com.binamultimediaindonesia.waygoldendjaya.representation.navigation.BottomNavigationBar
import com.binamultimediaindonesia.waygoldendjaya.representation.navigation.Navigation
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.WayGoldenDjayaTheme
import com.binamultimediaindonesia.waygoldendjaya.service.NotificationService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity(), ActivityCallback {


    @Inject
    lateinit var storeUserData: StoreUserDataRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)





        setContent {
            WayGoldenDjayaTheme {


                MainScreen()
            }
        }
    }




    @Composable
    fun MainScreen() {


        val navController = rememberNavController()

        var showBottomBar by rememberSaveable { mutableStateOf(true) }
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        showBottomBar = when (navBackStackEntry?.destination?.route) {
            "home" -> true
            "profile" -> true
            "schedules" -> true// bottom navbar disembunyikan ketika di halaman login
            else -> false // in all other cases show bottom bar
        }

        Scaffold(bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(
                    items = BOTTOM_MENU_LIST,
                    navController = navController,
                    onItemClick = { item -> navController.navigate(item.route) }
                )
            }

        }, content = { it ->


            val padding = it
            Navigation(navController = navController, this)


        })
    }


    override fun startStreaming() {

        val intent = Intent(this, StreamingActivity::class.java)

        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)

        startActivity(intent)


    }

    override fun startService(groupName: String) {
        val serviceIntent = Intent(this, NotificationService::class.java)

        val trimmedGroupName = groupName.replace(" ","_")

        NotificationService.apply {
            IS_ACTIVE = true
            GROUP_NAME = trimmedGroupName
        }

        startService(serviceIntent)
    }

    override fun downloadData(url: String, token:String) {
       val androidDownload = AndroidDownloader(this)

        androidDownload.downloadFile(url, token)
    }
}

interface ActivityCallback {

    fun startStreaming()

    fun startService(groupName:String)

    fun downloadData(url:String, token:String)

}




