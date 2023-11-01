package com.binamultimediaindonesia.waygoldendjaya


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.binamultimediaindonesia.waygoldendjaya.common.Constants.BOTTOM_MENU_LIST
import com.binamultimediaindonesia.waygoldendjaya.datastore.abstraction.StoreUserDataRepository
import com.binamultimediaindonesia.waygoldendjaya.representation.navigation.BottomNavigationBar
import com.binamultimediaindonesia.waygoldendjaya.representation.navigation.Navigation
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.WayGoldenDjayaTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity(), StartStreaming {



    @Inject
    lateinit var storeUserData : StoreUserDataRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      //  startStreaming(false)


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
            "login" -> false // bottom navbar disembunyikan ketika di halaman login
            else -> true // in all other cases show bottom bar
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











    override fun startStreaming(isHost: Boolean,room:String, username: String, userId: String, muthawif:String) {

        val intent = Intent(this, StreamingActivity::class.java)
        intent.putExtra("username", username)
        intent.putExtra("userid",userId)
        intent.putExtra("room",room)
        intent.putExtra("host",isHost)
        intent.putExtra("muthawif",muthawif)
        startActivity(intent)
        finish()

    }
}

interface StartStreaming {

    fun startStreaming(isHost:Boolean,room:String, username:String, userId:String,muthawif:String)

}



