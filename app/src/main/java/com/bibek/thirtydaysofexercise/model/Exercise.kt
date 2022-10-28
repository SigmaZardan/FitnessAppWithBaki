package com.bibek.thirtydaysofexercise.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Exercise(

    val day : Int ,
    @StringRes val titleRes :  Int,
    @DrawableRes val imageRes: Int ,
    @StringRes val descriptionRes : Int


)



