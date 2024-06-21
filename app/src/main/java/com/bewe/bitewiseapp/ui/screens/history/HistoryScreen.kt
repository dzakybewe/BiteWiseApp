package com.bewe.bitewiseapp.ui.screens.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.bewe.bitewiseapp.data.remote.model.HistoryItem
import com.bewe.bitewiseapp.ui.theme.Orange

/* Feature don't work because history was added when user clicked
* on an item (calling detail from API). Since the endpoint for restaurant
* detail don't work. The history also not able to show */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = viewModel(
        factory = ViewModelFactory.getAuthInstance(LocalContext.current)
    ),
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.history()
    }
    val result by viewModel.result.collectAsState()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Your History",
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                },
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text("Feature will be available soon")
        }
//        when (result) {
//            is UiState.Loading -> {
//                CircularProgressIndicator()
//            }
//
//            is UiState.Error -> {
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(innerPadding),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text((result as UiState.Error).errorMessage)
//                }
//            }
//
//            is UiState.Success -> {
//                Column(
//                    modifier = Modifier
//                        .padding(innerPadding)
//                        .padding(top = 48.dp, start = 34.dp, end = 34.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    HistoryContent(itemsList = (result as UiState.Success<HistoryResponse>).data.data.history)
//                }
//            }
//        }

    }
}

@Composable
private fun HistoryContent(
    modifier: Modifier = Modifier,
    itemsList: List<HistoryItem>,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        items(itemsList) {
            HistoryItem(
                item = it
            )
        }
    }
}

@Composable
private fun HistoryItem(
    modifier: Modifier = Modifier,
    item: HistoryItem,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(48.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.sample_picture),
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Orange),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = item.restaurant.name,
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}