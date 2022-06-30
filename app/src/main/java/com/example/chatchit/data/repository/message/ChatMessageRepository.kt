package com.example.chatchit.data.repository.message

import androidx.lifecycle.LiveData
import com.example.chatchit.data.roomdb.entity.MessageEntity
import kotlinx.coroutines.flow.Flow

interface ChatMessageRepository {
   suspend fun getMessage(roomId : String) : LiveData<String>
   suspend fun sendMessage(message: String, roomId : String)
   suspend fun getListMessage() : LiveData<List<MessageEntity>>
   suspend fun insertMessage(message: MessageEntity)
}