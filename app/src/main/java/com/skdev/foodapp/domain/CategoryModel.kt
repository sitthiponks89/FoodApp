package com.skdev.foodapp.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryModel(

    @Json(name = "Id")
    var Id: Int = 0,
    @Json(name = "ImagePath")
    var ImagePath: String = "",
    @Json(name = "Name")
    var Name: String = ""

) : Parcelable