package com.betterfilter.networking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.betterfilter.networking.extensions.debug
import com.betterfilter.networking.extensions.enqueue
import com.betterfilter.networking.extensions.info
import com.betterfilter.networking.model.User
import com.betterfilter.networking.service.DataService
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = UsersAdapter(listOf())

        GlobalScope.launch {
            val users = getUsers()
            info(users)
            updateUI(users ?: listOf())
        }
    }



    suspend fun getUsers(): List<User>? = withContext(Dispatchers.IO) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val dataApi = retrofit.create(DataService::class.java)

        dataApi.getUsers().execute().body()
    }

    suspend fun updateUI(users: List<User>) = withContext(Dispatchers.Main) {
        recyclerview.adapter = UsersAdapter(users)
    }
}
