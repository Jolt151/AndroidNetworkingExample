package com.betterfilter.networking.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Post(val userId: Int, val id: Int, val title: String, val body: String)
