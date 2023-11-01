package com.binamultimediaindonesia.waygoldendjaya.representation.login


import android.content.Context
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.binamultimediaindonesia.waygoldendjaya.R
import com.binamultimediaindonesia.waygoldendjaya.common.BigTextComponent
import com.binamultimediaindonesia.waygoldendjaya.common.Constants.toJson
import com.binamultimediaindonesia.waygoldendjaya.common.NormalTextComponent
import com.binamultimediaindonesia.waygoldendjaya.common.SmallText
import com.binamultimediaindonesia.waygoldendjaya.common.Util.toastShort
import com.binamultimediaindonesia.waygoldendjaya.representation.login.components.MyTextInput
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Accent
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Shapes
import kotlinx.coroutines.delay
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel<LoginViewModel>(),
    context: Context = LocalContext.current

) {

    val state = viewModel.state.value

    val visibility = remember {
        mutableStateOf(false)
    }
    val nameValue = remember {
        mutableStateOf(TextFieldValue())
    }

    val pinValue = remember {
        mutableStateOf(TextFieldValue())
    }


    loginChecker(
        state = state,
        navController = navController,
        context = context,

    )


    if (state.loginData == null) {
        LaunchedEffect(true) {
            // Menunggu beberapa waktu (delay) sebelum mengubah `visibility` menjadi `true`
            delay(1200) // Ubah angka sesuai dengan durasi delay yang Anda inginkan (dalam milidetik)
            visibility.value = true

        }

    }


    Surface(
        modifier = Modifier
            .fillMaxSize(),

        ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.background),
                    contentScale = ContentScale.Crop
                )
                .padding(20.dp)
        )
        {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,


                ) {

                Image(
                    painter = painterResource(id = R.drawable.logowgd),
                    contentDescription = null,
                    modifier = Modifier.size(250.dp)
                )

                AnimatedVisibility(visible = visibility.value, enter = fadeIn(), exit = fadeOut()) {
                    Column {
                        Spacer(
                            modifier = Modifier.height(40.dp)
                        )
                        BigTextComponent(
                            value = stringResource(id = R.string.login_caps),
                            color = Color.White
                        )
                        SmallText(
                            value = stringResource(id = R.string.login_desc),
                            Accent
                        )
                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )
                        MyTextInput(
                            labelVal = stringResource(id = R.string.login_name),
                            type = KeyboardType.Text,
                            modifier = Modifier.fillMaxWidth(),
                            textValue = nameValue
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        MyTextInput(
                            labelVal = stringResource(id = R.string.login_id),
                            type = KeyboardType.Password,
                            modifier = Modifier.fillMaxWidth(),
                            textValue = pinValue
                        )
                        Button(
                            onClick = {

                                proccedLogin(
                                    nameValue.value.text,
                                    pinValue.value.text,
                                    viewModel,
                                    context
                                )

                            },
                            shape = Shapes.medium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 15.dp),
                            colors = ButtonDefaults.buttonColors(Accent)
                        ) {
                            if (state.isLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(25.dp),
                                    color = Color.White
                                )
                            } else {
                                NormalTextComponent(
                                    stringResource(id = R.string.login_caps),
                                    Color.White
                                )
                            }


                        }
                        Spacer(
                            modifier = Modifier.height(60.dp)
                        )
                        SmallText(
                            value = stringResource(id = R.string.by),
                            Accent
                        )
                        NormalTextComponent(
                            stringResource(id = R.string.developer),
                            Color.White
                        )
                    }

                }


            }

        }
    }
}

private fun validateLogin(name: String, pin: String): Boolean {

    return name != "" && pin != ""

}


private fun proccedLogin(
    name: String,
    pin: String,
    viewModel: LoginViewModel,

    context: Context
) {


    if (validateLogin(
            name,
            pin
        )
    ) {

        Log.d("loginvm", "masuk validasi")

        viewModel.proccedLogin(
            name,
            pin
        )


    } else {

        toastShort(R.string.validasi_login, context)


    }


}

@Composable
private fun loginChecker(
    state: LoginState,
    navController: NavController,
    context: Context,
) {


    //login ketika tekan tombol
    state.loginData?.let { data ->



        if (data.access) {
            LaunchedEffect(key1 = true) {

                navController.navigate("home") {
                    popUpTo("login") {
                        inclusive = true
                    }
                }


            }


        } else {

            toastShort(R.string.user_salah, context)

        }

    }

}

@Preview(showBackground = true)
@Composable
fun Login() {

    //  LoginScreen()


}