package com.bewe.bitewiseapp.ui.screens.home

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun HomeScreen(modifier: Modifier = Modifier) {
    val itemList = listOf(1, 2, 3, 4, 5, 6) // Cuma buat tes
    val popularList = listOf(1, 2, 3) // Cuma buat tes
    Scaffold {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(

                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo"
            )

            GreetingSection()

            HomeContent(itemList = itemList)

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

            HomeContent(itemList = popularList)
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

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    itemList: List<Int>,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier,
    ) {
        items(itemList) {
            RestaurantTile()
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    BiteWiseAppTheme {
        HomeScreen()
    }
}