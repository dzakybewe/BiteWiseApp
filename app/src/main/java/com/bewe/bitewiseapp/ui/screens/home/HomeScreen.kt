package com.bewe.bitewiseapp.ui.screens.home

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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bewe.bitewiseapp.R
import com.bewe.bitewiseapp.ui.components.RestaurantTile
import com.bewe.bitewiseapp.ui.theme.BiteWiseAppTheme
import com.bewe.bitewiseapp.ui.theme.Orange

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: () -> Unit,
) {
    val itemList = listOf(1, 2, 3, 4, 5, 6) // Cuma buat tes
    val popularList = listOf(1, 2, 3) // Cuma buat tes

    val locations = listOf("Select Location", "Jaksel", "Jakbar", "Jaktim")
    var expanded by remember { mutableStateOf(false) }
    var selectedLocation by remember { mutableStateOf(locations[0]) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(30.dp),
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
                onExpandedChange = { expanded = it },
                onLocationClick = { selectedLocation = it }
            )

            GreetingSection()

            HomeContent(itemList = itemList, navigateToDetail = navigateToDetail)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "top rated",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Orange
                    ),
                )
                Text(
                    text = " foods 🔥",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    ),
                )
            }

            HomeContent(itemList = popularList, navigateToDetail = navigateToDetail)
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
                text = " Bewe!",
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
    itemList: List<Int>,
    navigateToDetail: () -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.background(Color.White),
    ) {
        items(itemList) {
            RestaurantTile(modifier = Modifier.clickable {
                navigateToDetail()
            })
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    BiteWiseAppTheme {
        HomeScreen {

        }
    }
}