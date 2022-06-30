package com.example.chatchit.presentation

import androidx.lifecycle.*
import com.example.chatchit.data.repository.message.ChatMessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(private val repository: ChatMessageRepository) : ViewModel(){

   suspend fun getMessage(roomId: String): LiveData<String> {
       return repository.getMessage(roomId)
   }

    suspend fun sendMessage(message: String, roomId: String) {
        repository.sendMessage(message, roomId)
    }
}