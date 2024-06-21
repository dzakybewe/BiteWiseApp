package com.bewe.bitewiseapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bewe.bitewiseapp.ui.screens.preferences.PreferencesItem
import com.bewe.bitewiseapp.ui.theme.Orange

@Composable
fun PreferencesTile(
    isClicked: Boolean,
    modifier: Modifier = Modifier,
    preferenceItem: PreferencesItem,
) {
    Card(
        border = if (isClicked) {
            BorderStroke(
                width = 3.dp,
                color = Orange
            )
        } else { null },
        modifier = modifier.size(130.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = preferenceItem.imageUrl),
                contentDescription = "Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }

    }
}
