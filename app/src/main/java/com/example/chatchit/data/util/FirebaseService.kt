package com.example.chatchit.data.util

import com.google.firebase.database.FirebaseDatabase

object FirebaseService {
    private var instance : FirebaseDatabase? = null
    private const val BASE_URL = "https://chatchitapp-a3b67-default-rtdb.asia-southeast1.firebasedatabase.app/"
    fun getInstance() : FirebaseDatabase{
        if (instance == null){
            return FirebaseDatabase.getInstance(BASE_URL)
        }
        return  instance!!
    }
}

