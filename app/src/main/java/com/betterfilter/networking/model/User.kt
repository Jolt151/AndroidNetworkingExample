package com.betterfilter.networking.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity
data class User(@field:Json(name = "id") @PrimaryKey val id: String,
                @field:Json(name = "name") val name: String,
                @field:Json(name = "username") val username: String,
                val email: String//,
                //val address: Address
)

@JsonClass(generateAdapter = true)
@Entity
data class Address(val city: String, val zipcode: String)