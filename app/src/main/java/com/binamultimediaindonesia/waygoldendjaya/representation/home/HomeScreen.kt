package com.binamultimediaindonesia.waygoldendjaya.representation.home


import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import com.binamultimediaindonesia.waygoldendjaya.R
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.binamultimediaindonesia.waygoldendjaya.common.Constants.MENU_LIST
import com.binamultimediaindonesia.waygoldendjaya.representation.home.components.DestinationItem
import com.binamultimediaindonesia.waygoldendjaya.representation.home.components.MenuItem
import com.binamultimediaindonesia.waygoldendjaya.representation.home.components.MultiColorText
import com.binamultimediaindonesia.waygoldendjaya.representation.login.components.MyTextInput
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Accent
import com.binamultimediaindonesia.waygoldendjaya.representation.ui.theme.Primary

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewmodel: HomeScreenViewmodel = hiltViewModel(),
    navController: NavController

) {


    val model = ImageRequest.Builder(LocalContext.current)
    val state = viewmodel.state.value

    val lazyListState = rememberLazyListState()

    val searchValue = remember {
        mutableStateOf(TextFieldValue())
    }



    Surface()
    {
        Box {

            state.homeScreenData?.let {
                Column(modifier = modifier.verticalScroll(rememberScrollState())

                ) {
                    Box(
                        modifier = modifier
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

                            .padding(25.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(28.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Icon(
                                    painter = painterResource(id = R.drawable.ic_grid),
                                    contentDescription = "icon logo",
                                    tint = Accent
                                )

                                Spacer(modifier = modifier.width(15.dp))


                                MyTextInput(
                                    labelVal = "Search Here",
                                    type = KeyboardType.Text,
                                    modifier = modifier.fillMaxWidth(),
                                    trailingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.Search,
                                            contentDescription = "tempat search",
                                            tint = Primary
                                        )
                                    },
                                    textValue = searchValue
                                )

                            }

                            Row(
                                modifier = modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween

                            ) {
                                Column(
                                    modifier = modifier
                                        .width(230.dp)
                                        .heightIn(min = 50.dp),
                                    verticalArrangement = Arrangement.spacedBy(5.dp)
                                ) {
                                    // nanti diisi sesuai dengan nama user
                                    MultiColorText(text = it.user?.name ?: "Error")

                                    Row(
                                        modifier = modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        Icon(
                                            imageVector = Icons.Default.LocationOn,
                                            contentDescription = "Lokasi hotel",
                                            tint = Accent
                                        )

                                        Spacer(modifier = modifier.width(5.dp))

                                        it.user?.hotel?.let { it1 -> Text(text = it1.hotel_name, color = Accent) }

                                    }
                                }

                                CoilImage(model = model, url = it.user!!.profile_image)

                            }

                        }

                    }

                    Spacer(modifier = modifier.height(20.dp))

                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 25.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "Fitur",
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
                    Spacer(modifier = modifier.height(15.dp))

                    LazyVerticalGrid(
                        modifier = modifier.padding(horizontal = 15.dp).heightIn(max = 300.dp),
                        columns = GridCells.Fixed(4),
                        content = {
                            items(MENU_LIST.size) { index ->

                                MenuItem(menu = MENU_LIST[index]){
                                    navController.navigate(MENU_LIST[index].route)
                                }
                            }
                        })
                    Spacer(modifier = modifier.height(20.dp))
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 25.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = stringResource(id = R.string.wisata_religi),
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

                    Spacer(modifier = modifier.height(15.dp))

                    LazyRow(
                        modifier = Modifier.padding(bottom = 100.dp),
                        state = lazyListState
                    ) {

                        items(items = it.destinations) { each ->
                            DestinationItem(each, model)
                        }

                    }


                }
            }
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


@Composable
fun CoilImage(model: ImageRequest.Builder, url: String) {
    AsyncImage(
        model = model
            .data(url)
            .decoderFactory(SvgDecoder.Factory())
            .crossfade(true)
            .build(),
        modifier = Modifier
            .aspectRatio(1f)
            .size(70.dp)
            .clip(CircleShape),

        contentScale = ContentScale.Crop,
        contentDescription = "Profile Image"
    )
}

