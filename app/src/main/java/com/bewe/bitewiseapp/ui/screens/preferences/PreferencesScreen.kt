package com.bewe.bitewiseapp.ui.screens.preferences

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bewe.bitewiseapp.R
import com.bewe.bitewiseapp.ui.components.PreferencesType

@Composable
fun PreferencesScreen(modifier: Modifier = Modifier) {
    val itemList = listOf(1, 2, 3, 4, 5, 6) // Cuma buat tes

    PreferencesContent(itemList = itemList)
}

@Composable
fun PreferencesContent(
    modifier: Modifier = Modifier,
    itemList: List<Int>,
) {
    Scaffold { innerPadding ->
        Box(
            modifier = modifier.fillMaxSize().padding(innerPadding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.preferences_background), // Replace with your image resource
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                PreferencesList(itemList = itemList)

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "choose your preferences!",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.White,
                    ),
                    modifier = Modifier.padding(bottom = 13.dp)
                )
                Button(
                    shape = RoundedCornerShape(15.dp),
                    onClick = {
                        // Handle button click
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFFFFA500)),
                    modifier = Modifier.padding(bottom = 50.dp)
                ) {
                    Text("next", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }

        }
    }
}

@Composable
fun PreferencesList(
    modifier: Modifier = Modifier,
    itemList: List<Int>,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 40.dp, vertical = 44.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier,
    ) {
        items(itemList) {
            PreferencesType()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreferencesScreenPreview() {
    PreferencesScreen()
}