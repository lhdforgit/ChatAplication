package com.example.chatchit.common

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.chatchit.data.roomdb.entity.MessageEntity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class FirebaseQueryLiveData<T>constructor(private val query: Query, entityClass : Class<T>) : LiveData<T>() {

    override fun onActive() {
        super.onActive()
        query.addValueEventListener(listener)
    }

    override fun onInactive() {
        super.onInactive()
        query.removeEventListener(listener)
    }

    private val listener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            kotlin.runCatching {
                value = dataSnapshot.getValue(entityClass) as T
            }
        }

        override fun onCancelled(error: DatabaseError) {
            Log.e("FirebaseQueryLiveData","DatabaseError: $error")
        }
    }
}