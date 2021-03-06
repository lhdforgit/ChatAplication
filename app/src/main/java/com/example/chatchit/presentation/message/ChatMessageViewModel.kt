package com.example.chatchit.presentation.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.chatchit.data.repository.message.ChatMessageRepository
import com.example.chatchit.data.roomdb.entity.MessageEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class ChatMessageViewModel @Inject constructor(private val repository: ChatMessageRepository) :
    ViewModel() {

    suspend fun getMessage(roomId: String): LiveData<MessageEntity> {
        return repository.getMessage(roomId)
    }

    suspend fun sendMessage(message: MessageEntity, roomId: String) {
        repository.sendMessage(message, roomId)
    }

    suspend fun getMessageList(): LiveData<List<MessageEntity>> {
        return repository.getListMessage()
    }

    suspend fun insertMessage(message: MessageEntity) {
        repository.insertMessage(message)
    }

    suspend fun getMessagePaging(roomId: String): Flow<PagingData<MessageEntity>>  {
       return repository.getMessagePageList(roomId).cachedIn(viewModelScope)

    }

}