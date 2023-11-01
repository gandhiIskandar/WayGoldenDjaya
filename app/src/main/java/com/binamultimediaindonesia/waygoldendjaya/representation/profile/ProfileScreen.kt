package com.binamultimediaindonesia.waygoldendjaya.representation.profile

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
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
    viewModel: ProfileScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val model = ImageRequest.Builder(LocalContext.current)

    val state = viewModel.state.value

    val fieldMap = remember {
     mutableMapOf<String,String>()
    }




    // lockValue.value =


    Surface(modifier = Modifier.fillMaxSize()) {

        state.data?.let {
            val imageUri = rememberSaveable { mutableStateOf(it.profile_image) }
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ) { uri: Uri? ->
                uri?.let {

                    imageUri.value = uri.toString()

               saveandUploadPict(context, uri, viewModel)




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
                            textValue = remember{mutableStateOf(TextFieldValue("Reset Pin"))},
                            icon = Icons.Default.Lock, true
                        )

                        TransparentTextField(
                            textValue = phoneValue,
                            icon = Icons.Default.Phone, false
                        ){ inputData ->
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
                            textValue = remember { mutableStateOf(TextFieldValue(it.session.program.program_name))},
                            icon = ImageVector.vectorResource(id = R.drawable.ic_baseline_miscellaneous_services_24),
                            true
                        )


                        TransparentTextField(
                            textValue = remember { mutableStateOf(TextFieldValue(it.group.group_name))},
                            icon = ImageVector.vectorResource(id = R.drawable.ic_baseline_group_24),
                            true
                        )

                        TransparentTextField(
                            textValue = nameValue,
                            icon = Icons.Default.Person, false
                        ){ inputData ->
                            fieldMap["name"] = inputData
                        }
                        TransparentTextField(
                            textValue = remember{ mutableStateOf(TextFieldValue(it.hotel.hotel_name))},
                            icon = ImageVector.vectorResource(id = R.drawable.ic_baseline_hotel),
                            true
                        )
                        TransparentTextField(
                            textValue = nomorKamarvalue,
                            icon = ImageVector.vectorResource(id = R.drawable.ic_baseline_local_hotel_24),
                            false
                        ){ inputData ->
                            fieldMap["room_number"] = inputData
                        }


                    }

                }

                Button(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 15.dp, bottom = 70.dp),
                    onClick = {}, shape = Shapes.large,
                    colors = ButtonDefaults.buttonColors(Primary)
                ) {

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
                        modifier = Modifier.background(color = Color.White, shape = CircleShape)
                            .clickable {
                                if(fieldMap.isNotEmpty()){

                                    viewModel.updateUserData(fieldMap)

                                }else {
                                    toastShort(R.string.user_update, context)
                                }

                            }
                        ,
                        imageVector = Icons.Default.Refresh,
                        tint = Primary,
                        contentDescription = "Update"
                    )


                }

            }
        }


    }


}




@Composable
fun TransparentTextField(
    textValue: MutableState<TextFieldValue>,
    icon: ImageVector,
    readOnly: Boolean,
    setMap: ((String) -> Unit)? =null
) {
    Row(
        modifier = Modifier

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

private fun saveandUploadPict(context:Context, uri: Uri, viewModel: ProfileScreenViewModel){

    CoroutineScope(Dispatchers.IO).launch{
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
                MultipartBody.Part.createFormData("profile_image", outputFile.name, outputFile.asRequestBody())

           viewModel.updatePhotoProfile(imagePart)



        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



    }




@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {


}