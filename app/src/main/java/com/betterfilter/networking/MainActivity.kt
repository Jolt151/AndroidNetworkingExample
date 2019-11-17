package com.betterfilter.networking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.betterfilter.networking.extensions.info
import com.betterfilter.networking.model.Post
import com.betterfilter.networking.model.User
import com.betterfilter.networking.service.AppDatabase
import com.betterfilter.networking.service.DataService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    private val dataApi = retrofit.create(DataService::class.java)

    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = UsersAdapter(listOf())

        db = Room.databaseBuilder(this, AppDatabase::class.java, "userDB").build()




        GlobalScope.launch {
            info("we didnt get any new data yet.")
            info("loading persisted data from room...")
            info(getPersistedUsers())

            val users = getUsers()
            persistUsers(users)
            info(users)
            updateUI(users ?: listOf())

            val single = getSingleUser("1")
            info(single)

            val posts = getPosts()
            info(posts)
        }
    }



    suspend fun getUsers(): List<User>? = withContext(Dispatchers.IO) {
        dataApi.getUsers().execute().body()
    }

    suspend fun updateUI(users: List<User>) = withContext(Dispatchers.Main) {
        recyclerview.adapter = UsersAdapter(users)
    }

    suspend fun getSingleUser(id: String): User? = withContext(Dispatchers.IO) {
        dataApi.getUser(id).execute().body()
    }

    suspend fun getPosts(): List<Post>? = withContext(Dispatchers.IO) {
        dataApi.getPosts().execute().body()
    }

    suspend fun persistUsers(users: List<User>?) = withContext(Dispatchers.IO) {
        users?.let {
            db.userDao().insertAll(users)
        }
    }

    suspend fun getPersistedUsers(): List<User> = withContext(Dispatchers.IO) {
        db.userDao().getAll()
    }
}
