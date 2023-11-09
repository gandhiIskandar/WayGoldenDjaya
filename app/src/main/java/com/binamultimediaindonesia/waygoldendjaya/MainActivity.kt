package com.binamultimediaindonesia.waygoldendjaya


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
            "home" -> true
            "profile" -> true
            "schedules" ->true// bottom navbar disembunyikan ketika di halaman login
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











    override fun startStreaming(isHost: Boolean, room:String, groupName: String, username:String, userUrl:String, userId: String, muthawif:String) {

        val intent = Intent(this, StreamingActivity::class.java)
        intent.putExtra("username", username)
        intent.putExtra("userid",userId)
        intent.putExtra("userUrl",userUrl)
        intent.putExtra("groupName",groupName)
        intent.putExtra("room",room)
        intent.putExtra("host",isHost)
        intent.putExtra("muthawif",muthawif)

        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)

        startActivity(intent)


    }
}

interface StartStreaming {

    fun startStreaming(isHost:Boolean,room:String,groupName:String, username:String, userUrl:String,userId:String,muthawif:String)

}




