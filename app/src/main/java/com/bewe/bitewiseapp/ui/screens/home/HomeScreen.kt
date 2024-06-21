package com.bewe.bitewiseapp.ui.screens.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bewe.bitewiseapp.R
import com.bewe.bitewiseapp.ViewModelFactory
import com.bewe.bitewiseapp.common.UiState
import com.bewe.bitewiseapp.data.remote.model.RecommendationResponse
import com.bewe.bitewiseapp.data.remote.model.RecommendationsItem
import com.bewe.bitewiseapp.ui.components.RestaurantTile
import com.bewe.bitewiseapp.ui.theme.Orange

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
    viewModel: HomeScreenViewModel = viewModel(
        factory = ViewModelFactory.getAuthInstance(LocalContext.current)
    ),
    context: Context = LocalContext.current,
) {

    val result by viewModel.result.collectAsState()
    val typeId by viewModel.typeId.collectAsState()

    val locations = listOf("Select Location", "Jaksel", "Jakbar", "Jaktim")
    var selectedLocation by remember { mutableStateOf(locations[0]) }

    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
            )

            LocationDropdown(
                expanded = expanded,
                selectedLocation = selectedLocation,
                locations = locations,
                onExpandedChange = {
                    Toast.makeText(
                        context,
                        "Feature will be available soon",
                        Toast.LENGTH_LONG
                    ).show()
//                    expanded = it
                },
                onLocationClick = { location ->
//                    selectedLocation = location
//                    val regionId = locations.indexOf(location)
//                    coroutineScope.launch {
//                        viewModel.recommendation(regionId)
//                    }
                }
            )

            GreetingSection()

            when (result) {
                is UiState.Loading -> {
                    CircularProgressIndicator()
                }

                is UiState.Error -> {
                    Text((result as UiState.Error).errorMessage)
                }

                is UiState.Success -> {
                    HomeContent(
                        itemList = (result as UiState.Success<RecommendationResponse>).data.data.recommendations,
                        navigateToDetail = navigateToDetail,
                        context = context
                    )
                }
            }
        }
    }
}

@Composable
fun GreetingSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row {
            Text(
                text = "hi",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Orange
                ),
            )
            Text(
                text = " BiteWiser!",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Row {
            Text(
                text = "we",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Orange
                ),
            )
            Text(
                text = " think you will",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
            )
            Text(
                text = " love",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Orange
                ),
            )
            Text(
                text = " this",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
            )
            Text(
                text = " <3",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Orange
                ),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDropdown(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    selectedLocation: String,
    onLocationClick: (String) -> Unit,
    locations: List<String>,
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { onExpandedChange(!expanded) },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(8.dp)
    ) {
        TextField(
            value = selectedLocation,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded,
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
        )

        ExposedDropdownMenu(
            expanded = expanded,
            modifier = Modifier.background(Color.White),
            onDismissRequest = { onExpandedChange(false) }) {
            locations.filter { it != "Select Location" }.forEach { location ->
                DropdownMenuItem(
                    text = { Text(text = location) },
                    onClick = {
                        onLocationClick(location)
                        onExpandedChange(false)
                    },
                    modifier = Modifier.background(Color.White)
                )
            }
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    itemList: List<RecommendationsItem>,
    navigateToDetail: (Int) -> Unit,
    context: Context,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .height(500.dp)
            .background(Color.White),
    ) {
        items(itemList) {
            RestaurantTile(
                item = it,
                modifier = Modifier.clickable {
//                    Log.d("Type", it.typeId.toString())
//                    navigateToDetail(it.id)
                    Toast.makeText(context, "Feature will be available soon", Toast.LENGTH_LONG)
                        .show()
                }
            )
        }
    }
}
