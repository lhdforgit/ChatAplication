package com.example.chatchit.data.repository.message

import androidx.lifecycle.LiveData
import com.google.firebase.database.DataSnapshot

interface ChatMessageRepository {
   suspend fun getMessage(roomId : String) : LiveData<String>
   suspend fun sendMessage(message: String, roomId : String)
}