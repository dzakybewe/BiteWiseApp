package com.bewe.bitewiseapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bewe.bitewiseapp.R
import com.bewe.bitewiseapp.data.remote.model.RecommendationsItem
import com.bewe.bitewiseapp.ui.theme.Orange

@Composable
fun RestaurantTile(
    modifier: Modifier = Modifier,
    item: RecommendationsItem,
) {
    Card(
        modifier = modifier.wrapContentSize(),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier.background(Orange).wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .background(Orange)
                    .fillMaxWidth()
                    .wrapContentSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mieayam),
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Orange),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.name,
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    ),
                    maxLines = 1,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
