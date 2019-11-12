package com.betterfilter.networking.service

import com.betterfilter.networking.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DataService {
    @GET("/users")
    fun getUsers(): Call<List<User>>

    @GET("/users/{id}")
    fun getUser(@Path("id") id: String): Call<User>

    @GET("/posts")
    fun getPosts()
}