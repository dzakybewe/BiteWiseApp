package com.bewe.bitewiseapp.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bewe.bitewiseapp.R
import com.bewe.bitewiseapp.ViewModelFactory
import com.bewe.bitewiseapp.common.UiState
import com.bewe.bitewiseapp.data.remote.model.DetailResponse
import com.bewe.bitewiseapp.ui.theme.Orange
import com.bewe.bitewiseapp.ui.theme.StarColor


/* Feature don't work because of forbidden calling GET Method with
* a request Body. The endpoint for getting detail restaurant, should
* include a request Body. But retrofit library won't let it happen, as it's
* actually also forbidden in HTTP */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    viewModel: DetailScreenViewModel = viewModel(
        factory = ViewModelFactory.getAuthInstance(LocalContext.current)
    ),
    id: Int,
) {
    val result by viewModel.result.collectAsState()

    LaunchedEffect(key1 = result) {
        viewModel.detailRestaurant(id)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        when (result) {
            is UiState.Loading -> {
                CircularProgressIndicator()
            }

            is UiState.Error -> {
                Box(
                    modifier = Modifier.padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text((result as UiState.Error).errorMessage)
                }
            }

            is UiState.Success -> {
                DetailContent(
                    modifier = Modifier.padding(innerPadding),
                    data = (result as UiState.Success<DetailResponse>).data.data.restaurant
                )
            }
        }

    }
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    data: DetailResponse.Restaurant
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 50.dp, start = 34.dp, end = 34.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.sample_picture),
            contentDescription = "Mie Ayam",
            modifier = Modifier
                .height(220.dp)
                .clip(shape = RoundedCornerShape(12.dp))
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                data.name,
                style = TextStyle(
                    color = Orange,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "",
                tint = StarColor,
            )

            Text(
                data.rating.toString(),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(start = 4.dp)
            )

            Text(
                data.reviews.size.toString(),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                ),
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Address",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )


            Text(
                data.address,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Start
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

