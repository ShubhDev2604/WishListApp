package com.lifehive.app.singletons

import android.content.Context
import androidx.room.Room
import com.lifehive.app.data.WishDatabase
import com.lifehive.app.repository.WishRepository

object Graph {
    lateinit var database: WishDatabase

    val wishRepository by lazy{
        WishRepository(wishDao = database.wishDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, WishDatabase::class.java, "lifehive.db").build()
    }

}