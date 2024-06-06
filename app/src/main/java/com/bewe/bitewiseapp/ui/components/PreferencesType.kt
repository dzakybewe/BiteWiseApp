package com.bewe.bitewiseapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bewe.bitewiseapp.R
import com.bewe.bitewiseapp.ui.theme.BiteWiseAppTheme
import com.bewe.bitewiseapp.ui.theme.Orange
import java.util.prefs.Preferences

@Composable
fun PreferencesType(
    modifier: Modifier = Modifier,
){
    Card(
        modifier = modifier.size(130.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ){
        Box(
            modifier = Modifier.background(Orange).weight(3f).fillMaxWidth(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.type1),
                contentDescription = "Preferences",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun PreferencesTypePreview() {
    BiteWiseAppTheme {
        PreferencesType()
    }
}