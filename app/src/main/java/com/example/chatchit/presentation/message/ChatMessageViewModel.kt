package com.example.chatchit.presentation.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.chatchit.data.repository.message.ChatMessageRepository
import com.example.chatchit.data.roomdb.entity.MessageEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ChatMessageViewModel @Inject constructor(private val repository: ChatMessageRepository) :
    ViewModel() {

    suspend fun getMessage(roomId: String): LiveData<String> {
        return repository.getMessage(roomId)
    }

    suspend fun sendMessage(message: String, roomId: String) {
        repository.sendMessage(message, roomId)
    }

    suspend fun getMessageList() : LiveData<List<MessageEntity>>{
        return repository.getListMessage()
    }
    suspend fun insertMessage(message: MessageEntity){
        repository.insertMessage(message)
    }
}