package com.kom.avengerslist.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Avenger(
    val name: String,
    val power : String,
    val profileDesc : String,
    val profilePictUrl : String,
) : Parcelable
