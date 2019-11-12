package com.betterfilter.networking.service

import com.betterfilter.networking.model.User
import retrofit2.Call
import retrofit2.http.GET

interface DataService {
    @GET("/users")
    fun getUsers(): Call<List<User>>

    @GET("/posts")
    fun getPosts()
}