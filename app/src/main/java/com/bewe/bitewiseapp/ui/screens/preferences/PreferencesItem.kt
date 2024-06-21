package com.bewe.bitewiseapp.ui.screens.preferences

import com.bewe.bitewiseapp.R

data class PreferencesItem(
    val restaurantName: String,
    val imageUrl: Int,
    val typeId: Int,
)
/*
* 1: Resto
* 2: Cafe
* 3: PKL
* 4: Bakery */
val listPreferencesItem = listOf(

    PreferencesItem(
        "Sambel Hejo Sambel Dadak",
        R.drawable.shsd_image,
        1
    ),
    PreferencesItem(
        "Union Cafe Senayan City",
        R.drawable.union_image,
        2
    ),
    PreferencesItem(
        "Warteg Putra Bahari",
        R.drawable.warteg_image,
        3
    ),
    PreferencesItem(
        "Holland Bakery",
        R.drawable.hollandbakery_image,
        4
    ),

)
