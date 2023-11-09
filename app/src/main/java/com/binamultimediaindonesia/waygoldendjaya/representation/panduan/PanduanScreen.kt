package com.binamultimediaindonesia.waygoldendjaya.representation.panduan

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import com.binamultimediaindonesia.waygoldendjaya.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.binamultimediaindonesia.waygoldendjaya.common.PanduanData.panduanList
import com.binamultimediaindonesia.waygoldendjaya.domain.model.Panduan
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Accent

@Composable
fun PanduanScreen() {
    Surface {

        val expandedItem = remember {
            mutableStateOf(-1)
        }

        Column {
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
                        verticalAlignment = Alignment.Bottom

                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 50.dp),
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {

                            Text(
                                text = "Panduan Doa-Doa Untuk Jamaah Umrah ", style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.payton))
                                ), color = Color.White
                            )


                        }


                    }

                }

            }

            LazyColumn {
                items(panduanList) { item ->

                    val index = panduanList.indexOf(item)

                    ExpandableCard(
                        panduan = item,
                        expanded = index == expandedItem.value,
                        onClickExpanded = {
                            expandedItem.value = if(expandedItem.value == index){
                                -1
                            }else{
                                index
                            }

                        },
                        index = index)


                }
            }


        }
    }
}

@Composable
fun ExpandableCard(
    panduan: Panduan,
    expanded: Boolean,
    onClickExpanded: (index: Int) -> Unit,
    index: Int
) {

    val transition = updateTransition(targetState = expanded, label = "trans")

    val iconRotationDeg by
    transition.animateFloat(label = "icon change") { state ->

        if (state) {
            0f
        } else {
            180f
        }

    }
    val color by transition.animateColor(label = "color change") { state ->

        if (state) {
            Color.Gray.copy(0.5f)
        } else {
            MaterialTheme.colors.surface
        }

    }


    Card() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    ,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(text = panduan.judul, fontWeight = FontWeight.Medium)
                Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null,
                    modifier = Modifier
                        .rotate(iconRotationDeg)
                        .clickable {
                            onClickExpanded(index)
                        }
                )


            }
            Spacer(modifier = Modifier.size(16.dp))

            ExpandableContent(isExpanded = expanded, panduan = panduan )
        }
    }


}

@Composable
fun ExpandableContent(
    isExpanded: Boolean,
    panduan: Panduan
) {
    val enterTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(300)
        ) + fadeIn(initialAlpha = .3f, animationSpec = tween(300))
    }

    val exitTransition = remember {
        shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(300)
        ) + fadeOut(animationSpec = tween(300))
    }

    AnimatedVisibility(visible = isExpanded, enter = enterTransition, exit = exitTransition) {

       Column{

            Text(text = panduan.doa)
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = panduan.latin, textAlign = TextAlign.Justify, style = TextStyle(
                fontStyle = FontStyle.Italic
            )
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = panduan.arti, textAlign = TextAlign.Justify)

        }


    }
}