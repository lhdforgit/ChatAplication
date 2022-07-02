package com.example.chatchit.data.repository.message

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.PagingData
import com.example.chatchit.data.roomdb.entity.MessageEntity
import kotlinx.coroutines.flow.Flow

interface ChatMessageRepository {
   suspend fun getMessage(roomId : String) : LiveData<MessageEntity>
   suspend fun sendMessage(message: MessageEntity, roomId : String)
   suspend fun getListMessage() : LiveData<List<MessageEntity>>
   suspend fun insertMessage(message: MessageEntity)

   suspend fun getMessagePageList(roomId: String) : Flow<PagingData<MessageEntity>>
}