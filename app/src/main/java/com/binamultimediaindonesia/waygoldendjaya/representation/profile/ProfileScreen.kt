package com.binamultimediaindonesia.waygoldendjaya.representation.profile

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.binamultimediaindonesia.waygoldendjaya.R
import com.binamultimediaindonesia.waygoldendjaya.representation.profile.components.InformationItem
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Accent
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Primary
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Shapes

@Composable
fun ProfileScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {

        Column {
            Box(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Box {
                        Image(
                            painter = painterResource(id = R.drawable.sample_user),
                            modifier = Modifier
                                .size(height = 100.dp, width = 100.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop,

                            contentDescription = null,

                            )
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier
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
                        text = "Alfath Gandhi Iskandar",
                        style = TextStyle(
                            color = Accent,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "+6282119978",
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
                    InformationItem("Reset PIN", Icons.Default.Lock)
                    InformationItem("+6281377981", Icons.Default.Phone)

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

                    InformationItem("Nama Program",
                        ImageVector.vectorResource(id = R.drawable.ic_baseline_miscellaneous_services_24) )


                    InformationItem("Group A",
                        ImageVector.vectorResource(id = R.drawable.ic_baseline_group_24) )

                    InformationItem("Nama Jamaah", Icons.Default.Person)
                    InformationItem("Nama Hotel",
                        ImageVector.vectorResource(id = R.drawable.ic_baseline_hotel))
                    InformationItem("Nomor Kamar",
                        ImageVector.vectorResource(id = R.drawable.ic_baseline_local_hotel_24))



                }

            }

            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 15.dp),
                onClick ={} ,shape= Shapes.large,
                colors = ButtonDefaults.buttonColors(Primary) ) {

                Text(
                    text ="UPDATE",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )

                Spacer(modifier = Modifier.width(10.dp))

                Icon(
                    modifier = Modifier.background(color = Color.White, shape= CircleShape),
                    imageVector = Icons.Default.Refresh,
                    tint = Primary,
                    contentDescription = "Update" )



            }

        }

    }


}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {

    ProfileScreen()

}