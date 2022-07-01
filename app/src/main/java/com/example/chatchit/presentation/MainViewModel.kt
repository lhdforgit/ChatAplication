package com.example.chatchit.presentation

import androidx.lifecycle.*
import com.example.chatchit.data.repository.message.ChatMessageRepository
import com.example.chatchit.data.roomdb.entity.MessageEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(private val repository: ChatMessageRepository) : ViewModel(){

}