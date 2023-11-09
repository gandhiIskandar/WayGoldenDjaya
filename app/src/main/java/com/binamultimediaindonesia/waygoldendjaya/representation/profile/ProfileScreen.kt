package com.binamultimediaindonesia.waygoldendjaya.representation.profile

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.binamultimediaindonesia.waygoldendjaya.R
import com.binamultimediaindonesia.waygoldendjaya.common.Util.toastShort
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Accent
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Primary
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Shapes
import kotlinx.coroutines.*
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream


@Composable
fun ProfileScreen(
    viewModel: ProfileScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current

    val model = ImageRequest.Builder(LocalContext.current)

    val state = viewModel.state.value

    val modifier = Modifier

    val fieldMap = remember {
        mutableMapOf<String, String>()
    }

    val dialogSwitch = remember {
        mutableStateOf(false)
    }
    val confirmationSwitch = remember {
        mutableStateOf(false)
    }

    // lockValue.value =


    Surface(modifier = Modifier.fillMaxSize()) {


        state.data?.let {
            val pinValue = remember {
                mutableStateOf(TextFieldValue())
            }
            val imageUri = rememberSaveable { mutableStateOf(it.profile_image) }
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ) { uri: Uri? ->
                uri?.let {

                    imageUri.value = uri.toString()

                    saveandUploadPict(context, uri, viewModel)


                }
            }

            if (dialogSwitch.value) {
                ResetPINDialog(
                    modifier = Modifier,
                    textValue = pinValue,
                    context = context,
                    switch = dialogSwitch,
                    viewModel = viewModel
                )
            }

            if(confirmationSwitch.value){
                ConfirmDialog(openDialog = confirmationSwitch) {

                    viewModel.userLogout {
                        navController.navigate("login") {
                            popUpTo("profile") {
                                inclusive = true
                            }
                        }
                    }

                }
            }

            val nameValue = remember {
                mutableStateOf(TextFieldValue(it.name))
            }


            val phoneValue = remember {
                mutableStateOf(TextFieldValue(it.phone_number))
            }

            val nomorKamarvalue = remember {
                mutableStateOf(TextFieldValue(it.room_number))
            }

            if (viewModel.updateState.value.message != "" && !dialogSwitch.value) {
                Toast.makeText(context, viewModel.updateState.value.message, Toast.LENGTH_LONG)
                    .show()
            }


            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {


                Box(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {


                        Box {


                            CoilImage(model, imageUri.value)
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier
                                    .clickable {
                                        launcher.launch("image/*")
                                    }
                                    .align(Alignment.BottomEnd)
                                    .background(
                                        color = Accent,
                                        shape = CircleShape,

                                        )
                                    .padding(5.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = it.name,
                            style = TextStyle(
                                color = Accent,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = it.phone_number,
                            style = TextStyle(
                                color = Primary,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )


                    }

                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(vertical = 10.dp)
                        .padding(start = 20.dp),
                    text = "General Settings",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Primary
                    ),
                )

                Box(modifier = Modifier.fillMaxWidth()) {
                    Column {


                        TransparentTextField(
                            textValue = remember { mutableStateOf(TextFieldValue("Reset Pin")) },
                            onClick = {
                                dialogSwitch.value = true
                            },
                            icon = Icons.Default.Lock,
                            readOnly = true

                        )




                        TransparentTextField(
                            textValue = phoneValue,
                            icon = Icons.Default.Phone, false
                        ) { inputData ->
                            fieldMap["phone_number"] = inputData

                        }


                    }

                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(vertical = 10.dp)
                        .padding(start = 20.dp),
                    text = "Information",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Primary
                    ),
                )

                Box(modifier = Modifier.fillMaxWidth()) {
                    Column {

                        TransparentTextField(
                            textValue = remember { mutableStateOf(TextFieldValue(it.session.program.program_name)) },
                            icon = ImageVector.vectorResource(id = R.drawable.ic_baseline_miscellaneous_services_24),
                            true
                        )


                        TransparentTextField(
                            textValue = remember { mutableStateOf(TextFieldValue(it.group.group_name)) },
                            icon = ImageVector.vectorResource(id = R.drawable.ic_baseline_group_24),
                            true
                        )

                        TransparentTextField(
                            textValue = nameValue,
                            icon = Icons.Default.Person, false
                        ) { inputData ->
                            fieldMap["name"] = inputData
                        }
                        TransparentTextField(
                            textValue = remember { mutableStateOf(TextFieldValue(it.hotel.hotel_name)) },
                            icon = ImageVector.vectorResource(id = R.drawable.ic_baseline_hotel),
                            true
                        )
                        TransparentTextField(
                            textValue = nomorKamarvalue,
                            icon = ImageVector.vectorResource(id = R.drawable.ic_baseline_local_hotel_24),
                            false
                        ) { inputData ->
                            fieldMap["room_number"] = inputData
                        }


                    }

                }

                Row(modifier = modifier.align(Alignment.CenterHorizontally)) {
                    Button(
                        modifier = Modifier
                            .padding(top = 15.dp, bottom = 70.dp),
                        onClick = {
                            if (fieldMap.isNotEmpty()) {

                                viewModel.updateUserData(fieldMap)

                            } else {
                                toastShort(R.string.user_update, context)
                            }
                        }, shape = Shapes.large,
                        colors = ButtonDefaults.buttonColors(Primary)
                    ) {
                        if (viewModel.updateState.value.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(25.dp),
                                color = Color.White
                            )
                        } else {
                            Text(
                                text = "UPDATE",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Icon(
                                modifier = Modifier
                                    .background(color = Color.White, shape = CircleShape),
                                imageVector = Icons.Default.Refresh,
                                tint = Primary,
                                contentDescription = "Update"
                            )

                        }

                    }

                    Button(
                        modifier = Modifier
                            .padding(top = 15.dp, bottom = 70.dp, start = 10.dp),
                        onClick = {

                        confirmationSwitch.value = true


                        }, shape = Shapes.large,
                        colors = ButtonDefaults.buttonColors(Color.Red)
                    ) {
                        if (viewModel.updateState.value.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(25.dp),
                                color = Color.White
                            )
                        } else {
                            Text(
                                text = "LOGOUT",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            )


                        }

                    }

                }

            }
        }

        if (state.data == null) {
            Box(modifier = modifier.fillMaxSize()) {
                if (state.error.isNotBlank()) {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
                }
            }
        }
    }
}


@Composable
fun ResetPINDialog(
    modifier: Modifier,
    textValue: MutableState<TextFieldValue>, context: Context, switch: MutableState<Boolean>,
    viewModel: ProfileScreenViewModel
) {
    Dialog(onDismissRequest = { }) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = Color.White
        ) {

            Box(contentAlignment = Alignment.Center) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = modifier.padding(10.dp)
                ) {

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = stringResource(id = R.string.pin_baru),
                            style = TextStyle(
                                fontSize = 22.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Medium
                            )
                        )


                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "",
                            tint = colorResource(android.R.color.darker_gray),
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .clickable {
                                    switch.value = false
                                }
                        )


                    }

                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp)
                            .border(
                                BorderStroke(
                                    width = 2.dp,
                                    color = Primary
                                ),
                                shape = RoundedCornerShape(20)
                            ),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        singleLine = true,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "",
                                tint = Primary,
                                modifier = Modifier
                                    .width(20.dp)
                                    .height(20.dp)
                            )
                        },
                        placeholder = { Text(text = "Masukan PIN Baru") },
                        value = textValue.value,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            if (it.text.length <= 6) {
                                textValue.value = it
                            }
                        })


                    Button(
                        onClick = {
                            if (textValue.value.text.isEmpty()) {

                                toastShort(R.string.pin_kosong, context)

                            } else {
                                viewModel.updatePinUser(textValue.value.text)

                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Primary, contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth(0.5f)
                            .height(50.dp)
                    ) {

                        if (viewModel.updateState.value.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(25.dp),
                                color = Color.White
                            )
                        } else {
                            Text(text = "Done")

                        }


                    }

                }

            }


        }

        if (viewModel.updateState.value.message != "" && switch.value) {
            Toast.makeText(context, viewModel.updateState.value.message, Toast.LENGTH_LONG).show()
            switch.value = false
        }


    }

}


@Composable
fun TransparentTextField(
    textValue: MutableState<TextFieldValue>,
    icon: ImageVector,
    readOnly: Boolean,
    onClick: (() -> Unit)? = null,
    setMap: ((String) -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .clickable {
                onClick?.invoke()
            }

            .fillMaxWidth()
            .padding(vertical = 10.dp),

        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    )
    {


        TextField(
            modifier = Modifier.fillMaxWidth(0.85f),
            value = textValue.value,
            onValueChange = { it ->
                textValue.value = it
                setMap?.invoke(it.text)


            },
            enabled = !readOnly,
            leadingIcon = {
                Icon(

                    imageVector = icon,
                    contentDescription = "iconku",
                    tint = Primary
                )

            },
            readOnly = readOnly,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent

            ),
            textStyle = TextStyle(

                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Primary

            )
        )

        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            tint = Color.LightGray

        )


    }
}

@Composable
fun CoilImage(model: ImageRequest.Builder, url: String) {


    AsyncImage(
        model = model
            .data(url)
            .fallback(R.drawable.sample_user)
            .decoderFactory(SvgDecoder.Factory())
            .crossfade(true)
            .build(),
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape),

        contentScale = ContentScale.Crop,
        contentDescription = "Profile Image"
    )
}

private fun saveandUploadPict(context: Context, uri: Uri, viewModel: ProfileScreenViewModel) {

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream)

            // Konversi gambar ke format PNG
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 67, outputStream)

            // Simpan Bitmap dalam format PNG di direktori cache
            val cacheDir = context.cacheDir
            val outputFile = File(cacheDir, "temp.jpg")

            val fileOutputStream = FileOutputStream(outputFile)
            fileOutputStream.write(outputStream.toByteArray())
            fileOutputStream.close()

            // Ganti dengan path file gambar Anda

            val imagePart =
                MultipartBody.Part.createFormData(
                    "profile_image",
                    outputFile.name,
                    outputFile.asRequestBody()
                )

            viewModel.updatePhotoProfile(imagePart)


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}

@Composable
fun ConfirmDialog(openDialog: MutableState<Boolean>, execute:()->Unit) {
    if (openDialog.value) {

        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Konfirmasi Logout")
            },
            text = {
                Text("Apakah Anda yakin ingin logout?")
            },
            confirmButton = {
                Button(colors = ButtonDefaults.buttonColors(
                    backgroundColor = Primary, contentColor = Color.White
                ),

                    onClick = {
                        openDialog.value = false
                        execute.invoke()

                    }) {
                    Text("Ya")

                }
            },
            dismissButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Primary, contentColor = Color.White
                    ),
                    onClick = {
                        openDialog.value = false
                    }) {
                    Text("Tidak")
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
//ResetPINDialog(modifier = Modifier,  remember{mutableStateOf(TextFieldValue())}, LocalContext.current)

}