package com.skdev.foodapp.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodModel(

    @Json(name = "BestFood")
    var BestFood: Boolean = false,
    @Json(name = "CategoryId")
    var CategoryId: String = "",
    @Json(name = "Description")
    var Description: String = "",
    @Json(name = "Id")
    var Id: Int = 0,
    @Json(name = "ImagePath")
    var ImagePath: String = "",
    @Json(name = "LocationId")
    var LocationId: Int = 0,
    @Json(name = "PriceId")
    var PriceId: Int = 0,
    @Json(name = "Price")
    var Price: Int = 0,
    @Json(name = "Star")
    var Star: Double = 0.0,
    @Json(name = "TimeId")
    var TimeId: Int = 0,
    @Json(name = "TimeValue")
    var TimeValue: Int = 0,
    @Json(name = "Title")
    var Title: String = "",
    @Json(name = "Calorie")
    var Calorie: Int = 0,
    @Json(name = "numberInCart")
    var numberInCart: Int = 0

): Parcelable
