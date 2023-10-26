package com.binamultimediaindonesia.waygoldendjaya.representation.schedule


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import com.binamultimediaindonesia.waygoldendjaya.R
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.binamultimediaindonesia.waygoldendjaya.representation.schedule.components.ScheduleItem
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Accent

@Composable
fun ScheduleScreen( viewModel:ScheduleViewModel= hiltViewModel()) {




  Surface(modifier = Modifier.fillMaxSize()) {

      Column{
          Box(
              modifier = Modifier
                  .fillMaxWidth()
                  .height(200.dp)
                  .graphicsLayer(
                      clip = true,
                      shape = RoundedCornerShape(bottomStart = 40.dp)
                  )
                  .paint(
                      painterResource(
                          id = R.drawable.background
                      ),
                      contentScale = ContentScale.Crop
                  )

                  .padding(horizontal = 25.dp)
                  .padding(top = 25.dp)
                  .padding(bottom = 5.dp)
          ) {
              Column(
                  verticalArrangement = Arrangement.spacedBy(20.dp)
              ) {
                  Row(
                      verticalAlignment = Alignment.CenterVertically
                  ) {

                      Icon(
                          painter = painterResource(id = R.drawable.ic_grid),
                          contentDescription = "icon logo",
                          tint = Accent
                      )

                      Spacer(modifier = Modifier.width(15.dp))




                  }

                  Row(
                      modifier = Modifier.fillMaxWidth(),
                      horizontalArrangement = Arrangement.SpaceBetween,
                      verticalAlignment = Alignment.Bottom

                  ) {
                      Column(
                          modifier = Modifier
                              .width(230.dp)
                              .heightIn(min = 50.dp),
                          verticalArrangement = Arrangement.spacedBy(5.dp)
                      ) {

                          Text(text = "Jadwal Hari Ini", style = TextStyle(
                              fontSize = 18.sp,
                              fontFamily = FontFamily(Font(R.font.payton))
                          ), color = Color.White)





                              Text(text = "Land Arrangement Umroh and Hajj",
                                  color = Color.White,
                              style= TextStyle(
                                  fontSize = 14.sp,

                              )
                              )

                          Text(text = "Way Golden Djaya Group",
                              color = Accent,
                              style= TextStyle(
                                  fontSize = 13.sp,

                                  )
                          )


                      }
                      Image(
                          painter = painterResource(id = R.drawable.sample_user),
                          modifier = Modifier

                              .size(height = 70.dp, width = 70.dp)
                              .clip(CircleShape),
                          contentScale = ContentScale.Crop,

                          contentDescription = null,

                          )

                  }

              }

          }

          Row(
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(horizontal = 25.dp, vertical = 15.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
          ) {

              Text(
                  text = "14 Agustus 2023",
                  color = Accent,
                  style = TextStyle(
                      fontSize = 22.sp,
                      fontWeight = FontWeight.Normal,

                      )
              )
              Icon(
                  imageVector = Icons.Default.MoreVert,
                  contentDescription = null,
                  tint = Accent
              )

          }

//          LazyColumn(modifier = Modifier.padding(horizontal = 25.dp)
//          ){
//              items(){ schedule ->
//
//                  ScheduleItem(schedule)
//
//              }
//          }


      }



  }

}

@Preview
@Composable
fun ScheduleScreenPreview() {
    ScheduleScreen()


}